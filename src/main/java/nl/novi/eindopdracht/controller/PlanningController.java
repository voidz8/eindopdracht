package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Planning;
import nl.novi.eindopdracht.service.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanningController {

    @Autowired
    private PlanningService planningService;

    @GetMapping(value = "/planning")
    public ResponseEntity<Object> getAllSchedules(){
        return new ResponseEntity<>(planningService.getAllSchedules(), HttpStatus.OK);
    }

    @GetMapping(value = "/planning/{id}")
    public ResponseEntity<Object> getPlanning(@PathVariable(value = "id") long id){
        return new ResponseEntity<>(planningService.getPlanning(id), HttpStatus.OK);
    }

    @PostMapping(value = "/planning")
    public ResponseEntity<Object> createPlanning(@RequestBody Planning planning){
        long newPlanning = planningService.createPlanning(planning);
        return new ResponseEntity<>("A new planning created with id: " + newPlanning, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/planning/{id}")
    public ResponseEntity<Object> deletePlanning(@PathVariable(value = "id") long id){
        planningService.deletePlanning(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/planning/{id}/orders")
    public ResponseEntity<Object> getOrders(@PathVariable(value = "id") long id){
        return new ResponseEntity<>(planningService.getOrders(id), HttpStatus.OK);
    }

    @PostMapping(value = "/planning/{id}/orders")
    public ResponseEntity<Object> addOrder(@PathVariable(value = "id") long id, @RequestBody Order order){
        planningService.addOrder(id, order);
        return new ResponseEntity<>("Successfully added order " + order.getOrderNumber() + " to " + id +".", HttpStatus.OK);
    }
    @DeleteMapping(value = "/planning/{id}/orders")
    public ResponseEntity<Object> removeOrder(@PathVariable(value = "id") long id, @RequestBody Order order){
        planningService.removeOrder(id, order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
