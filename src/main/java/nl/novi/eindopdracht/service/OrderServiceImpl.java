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
    public long createOrder(Order Order) {
        return 0;
    }

    @Override
    public void updateOrder(long id, Order order) {

    }

    @Override
    public void updateOrderPartial(long id, Client client, Product product, Machine machine, LocalDate productionDate, LocalDate deliveryDate) {

    }

    @Override
    public boolean orderExists(long id) {
        return false;
    }

}