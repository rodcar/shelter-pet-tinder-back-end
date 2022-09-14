package pe.rodcar.shelterpet.adoption.controller;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;

import pe.rodcar.shelterpet.adoption.entities.Pet;
import pe.rodcar.shelterpet.adoption.response.PetResponse;
import pe.rodcar.shelterpet.adoption.service.PetService;

@RestController
@RequestMapping("/pets")
public class PetController {

	@Autowired
	private PetService topicService;

	@ApiOperation("List of all pets")
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PetResponse>> fetchAll() {
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime thirtyDaysAgo = now.plusDays(-30);
		try {
			List<PetResponse> pets = new ArrayList<>();
			List<Pet> petsFounded = topicService.findAllByOrderByDateAddedDesc();

			for (Pet pet : petsFounded) {
				if (pet.getDateAdded().toInstant().isBefore(thirtyDaysAgo.toInstant())) {
					continue;
				}
				List<String> photos = new ArrayList<String>();
				photos.add(pet.getPhoto());
				PetResponse responseItem = new PetResponse(pet.getId(), pet.getType(), pet.getName(), pet.getAge(), pet.getBreed(),
						pet.getLocation(), pet.getStatus(), pet.getPhoneNumber(), photos);
				pets.add(responseItem);
			}

			return new ResponseEntity<List<PetResponse>>(pets, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Save a pet")
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveObjetive(@Validated @RequestBody Pet pet) {
		try {
			Pet o = topicService.save(pet);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(o.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
