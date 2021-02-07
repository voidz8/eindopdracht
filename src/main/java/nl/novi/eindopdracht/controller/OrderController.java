package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Repeatable;
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
    public ResponseEntity<Object> deleteOrder(@RequestParam(value = "id")long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/orders/{id}")
    public ResponseEntity<Object> updateOrder(@RequestParam(value = "id") long id, @RequestBody Order order){
        orderService.updateOrder(id, order);
        return new ResponseEntity<>("Order with ordernumber: "+ id + " is updated.", HttpStatus.OK);
    }
}
