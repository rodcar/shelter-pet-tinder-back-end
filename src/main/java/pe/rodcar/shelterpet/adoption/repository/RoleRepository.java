package pe.rodcar.shelterpet.adoption.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.rodcar.shelterpet.adoption.entities.Role;
import pe.rodcar.shelterpet.adoption.entities.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}