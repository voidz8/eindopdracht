package nl.novi.eindopdracht.repository;

import nl.novi.eindopdracht.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
