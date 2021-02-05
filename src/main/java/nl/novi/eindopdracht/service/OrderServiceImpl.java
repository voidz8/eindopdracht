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
    public long createOrder(long id, Order order) {
        if(!orderRepository.existsById(id)) {throw new OrderNotFoundException();}
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
    existingOrder.setDrawingNumber(order.getDrawingNumber());
    existingOrder.setProductionDate(order.getProductionDate());
    }

    @Override
    public void updateOrderPartial(long id, Client client, Product product, Machine machine, LocalDate productionDate, LocalDate deliveryDate) {

    }

    @Override
    public boolean orderExists(long id) {
        return false;
    }

}
