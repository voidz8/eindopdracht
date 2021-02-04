package nl.novi.eindopdracht.repository;

import nl.novi.eindopdracht.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
