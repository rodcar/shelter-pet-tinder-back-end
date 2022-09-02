package pe.rodcar.shelterpet.adoption.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.rodcar.shelterpet.adoption.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    //Boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsById(Long id);
}