package nl.novi.eindopdracht.repository;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findOrderByClient(Client client);
}
