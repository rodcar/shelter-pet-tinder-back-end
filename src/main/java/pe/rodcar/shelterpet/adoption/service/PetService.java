package pe.rodcar.shelterpet.adoption.service;

import java.util.Optional;

import pe.rodcar.shelterpet.adoption.entities.Pet;


public interface PetService extends CrudService<Pet> {

	Optional<Pet> findById(Long id);
	
}
