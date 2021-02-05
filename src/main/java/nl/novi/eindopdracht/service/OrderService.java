package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Product;
import nl.novi.eindopdracht.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface OrderService {

    Collection<Order> getAllOrders();
    Optional<Order> getOrderByOrderNumber(long id);
    void deleteOrder(long id);
    long createOrder(long id, Order Order);
    void updateOrder(long id, Order order);
    void updateOrderPartial(long id, Client client, Product product, Machine machine, LocalDate productionDate, LocalDate deliveryDate);
    boolean orderExists(long id);

}
