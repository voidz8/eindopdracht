package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.OrderNotFoundException;
import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Product;
import nl.novi.eindopdracht.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Collection<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderByOrderNumber(long id) {
        if(!orderRepository.existsById(id)) {throw new OrderNotFoundException(); }
        return orderRepository.findById(id);
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);

    }

    @Override
    public long createOrder(Order order) {
        Order newOrder = orderRepository.save(order);
        return newOrder.getOrderNumber();
    }

    @Override
    public void updateOrder(long id, Order order) {
    if(!orderRepository.existsById(id)) {throw new OrderNotFoundException();}
    Order existingOrder = orderRepository.findById(id).orElse(null);
    existingOrder.setClient(order.getClient());
    existingOrder.setOrderNumber(order.getOrderNumber());
    existingOrder.setDeliveryDate(order.getDeliveryDate());
    existingOrder.setOperations(order.getOperations());
    existingOrder.setProducts(order.getProducts());
    existingOrder.setProductionDate(order.getProductionDate());
    orderRepository.save(existingOrder);
    }

    @Override
    public void updateOrderPartial(long id, Map<String, Object> fields) {
    if(!orderRepository.existsById(id)) {throw new OrderNotFoundException();}
    Order order = orderRepository.findById(id).get();
    for (String field : fields.keySet()){
        switch (field.toLowerCase()){
            case "companyname":
                order.setClient((Client) fields.get(field));
                break;
            case "production_date":
                order.setProductionDate((LocalDate) fields.get(field));
                break;
            case "delivery_date":
                order.setDeliveryDate((LocalDate) fields.get(field));
                break;
            }
        }
        orderRepository.save(order);
    }

    @Override
    public boolean orderExists(long id) {
        return orderRepository.existsById(id);
    }

    @Override
    public Collection<Product> getProducts(long id) {
        if (!orderRepository.existsById(id)){throw new OrderNotFoundException();}
        Optional<Order> order = orderRepository.findById(id);
        return order.get().getProducts();
    }

    @Override
    public void addProduct(long id, Product product) {
        if (!orderRepository.existsById(id)){throw new OrderNotFoundException();}
        Order order = orderRepository.findById(id).get();
        order.addProduct(product);
        orderRepository.save(order);
    }

    @Override
    public void removeProduct(long id) {
        if (!orderRepository.existsById(id)){throw new OrderNotFoundException();}
        Order order = orderRepository.findById(id).get();
        for (Product product : order.getProducts()){
            order.removeProduct(product);
        }

    }
}
