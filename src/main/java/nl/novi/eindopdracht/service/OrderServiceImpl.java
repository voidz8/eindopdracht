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
    public void updateOrderPartial(long id, Client client, Product product, Machine machine, LocalDate productionDate, LocalDate deliveryDate, Order order) {
    if(!orderRepository.existsById(id)) {throw new OrderNotFoundException();}
    Order existingOrder = orderRepository.findById(id).orElse(null);
    if(existingOrder.getClient() != null){
        existingOrder.setClient(order.getClient());}
    if(existingOrder.getOperations() !=null){
        existingOrder.setOperations(order.getOperations());}
    if(existingOrder.getDeliveryDate() != null){
        existingOrder.setDeliveryDate(order.getDeliveryDate());}
    if(existingOrder.getProducts() != null){
        existingOrder.setProducts(order.getProducts());}
    if(existingOrder.getProductionDate() != null){
        existingOrder.setProductionDate(order.getProductionDate()); }
    orderRepository.save(existingOrder);
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
    public void addProductToOrder(long id, Product product) {
        if (!orderRepository.existsById(id)){throw new OrderNotFoundException();}
        Order order = orderRepository.findById(id).get();
        order.addProduct(product);
        orderRepository.save(order);
    }
}
