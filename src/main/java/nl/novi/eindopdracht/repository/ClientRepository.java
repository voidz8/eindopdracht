package nl.novi.eindopdracht.repository;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCompanyName(String companyName);
    boolean existsClientByCompanyName(String companyName);
    void deleteClientByCompanyName(String companyName);


}
