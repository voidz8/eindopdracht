package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Product;
import nl.novi.eindopdracht.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Repeatable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/orders")
    public ResponseEntity<Object> getAllOrders(){
        Collection<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @GetMapping(value = "/orders/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable("id") long id){
    return new ResponseEntity<>(orderService.getOrderByOrderNumber(id), HttpStatus.OK);
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<Object> createOrder(@RequestBody Order order){
        long newOrder = orderService.createOrder(order);
        return new ResponseEntity<>("Succesfully created order: "+newOrder+".", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/orders/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id")long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/orders/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable(value = "id") long id, @RequestBody Order order){
        orderService.updateOrder(id, order);
        return new ResponseEntity<>("Order with ordernumber: "+ id + " is updated.", HttpStatus.OK);
    }

    @PatchMapping(value = "/orders/{id}")
    public ResponseEntity<Object> updateOrderPartial(@PathVariable(value = "id") long id, Client client, Product product, Machine machine, LocalDate productionDate, LocalDate deliveryDate, @RequestBody Order order){
        orderService.updateOrderPartial(id,client, product, machine, productionDate, deliveryDate, order);
        return new ResponseEntity<>("Order with ordernumber"+ id +" is updated.", HttpStatus.OK);
    }
    @GetMapping(value = "/orders/{id}/products")
    public ResponseEntity<Object> getProducts(@RequestParam(value = "id") long id){
        Collection<Product> products = orderService.getProducts(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping(value = "/orders/{id}")
    public ResponseEntity<Object> addProducts(@RequestParam(value = "id") long id, Set<Product> products){
        orderService.addProductToOrder(id,products);
        return new ResponseEntity<>("Successfully added "+ products + " to "+ id, HttpStatus.OK);
    }

}
