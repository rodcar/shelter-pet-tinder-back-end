package pe.rodcar.shelterpet.adoption.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.rodcar.shelterpet.adoption.entities.Pet;
import pe.rodcar.shelterpet.adoption.repository.PetRepository;
import pe.rodcar.shelterpet.adoption.service.PetService;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	private PetRepository topicRepository;

	@Override
	public List<Pet> findAll() throws Exception {
		return topicRepository.findAll();
	}

	@Override
	public Pet save(Pet t) throws Exception {
		return topicRepository.save(t);
	}

	@Override
	public Pet update(Pet t) throws Exception {
		return topicRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		topicRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// Method not implemented
	}

	@Override
	public Optional<Pet> findById(Long id) {
		return topicRepository.findById(id);
	}
}
