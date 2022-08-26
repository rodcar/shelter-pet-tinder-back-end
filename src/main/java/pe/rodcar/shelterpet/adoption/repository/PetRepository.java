package pe.rodcar.shelterpet.adoption.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.rodcar.shelterpet.adoption.entities.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{
	
	Optional<Pet>  findById(Long id);
	
}
