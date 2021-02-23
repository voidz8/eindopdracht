package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.exceptions.BadRequestException;
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
import java.util.Map;
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
    public ResponseEntity<Object> updateOrderPartial(@PathVariable(value = "id") long id, @RequestBody Map<String, Object> fields){
        orderService.updateOrderPartial(id, fields);
        return new ResponseEntity<>("Order with ordernumber"+ id +" is updated.", HttpStatus.OK);
    }
    @GetMapping(value = "/orders/{id}/products")
    public ResponseEntity<Object> getProducts(@RequestParam(value = "id") long id){
        Collection<Product> products = orderService.getProducts(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping(value = "/orders/{id}/products")
    public ResponseEntity<Object> addProducts(@PathVariable(value = "id") long id, @RequestBody Product product){
        orderService.addProduct(id, product);
        return new ResponseEntity<>("Added " +product + "successfully", HttpStatus.OK);
    }
    @DeleteMapping(value = "/orders/{id}/products")
    public ResponseEntity<Object> removeProduct(@PathVariable(value = "id") long id){
        orderService.removeProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
