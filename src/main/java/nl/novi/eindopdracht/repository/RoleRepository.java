package nl.novi.eindopdracht.repository;

import nl.novi.eindopdracht.model.ERole;
import nl.novi.eindopdracht.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
