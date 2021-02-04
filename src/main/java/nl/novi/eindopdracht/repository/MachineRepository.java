package nl.novi.eindopdracht.repository;

import nl.novi.eindopdracht.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, Long> {
}
