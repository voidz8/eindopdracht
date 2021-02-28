package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Product;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;


public interface OrderService {

    Collection<Order> getAllOrders();
    Optional<Order> getOrderByOrderNumber(long id);
    void deleteOrder(long id);
    long createOrder(Order Order);
    void updateOrder(long id, Order order);
    void updateOrderPartial(long id, Map<String, Object> fields);
    Collection<Product> getProducts(long id);
    void addProduct(long id, Product product);
    void removeProduct(long id, Product product);
}
