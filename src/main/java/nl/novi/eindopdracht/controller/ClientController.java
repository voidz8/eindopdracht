package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.service.ClientService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<Object> getClients(){
        List<Client> clients = clientService.getallClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /*@GetMapping(value = "/clients/{companyname}")
    public ResponseEntity<Object> getClientByName(@PathVariable("company_name") String companyName){
        return new ResponseEntity<>(clientService.getClientByName(companyName), HttpStatus.OK);
    }
*/
    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable("id") long id){
        return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "clients/{id}")
    public ResponseEntity<Object> deleteClientById(@PathVariable("id") long id){
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<Object> createClient(@RequestBody Client client){
        String newName = clientService.createClient(client);
        return new ResponseEntity<>("A new client is created: "+ newName, HttpStatus.CREATED);
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable("id") long id, @RequestBody Client client){
        clientService.updateClient(id, client);
        return new ResponseEntity<>("Client with clientid: " + id +"is updated", HttpStatus.OK);
    }

    @PatchMapping(value = "/clients/{id}")
    public ResponseEntity<Object> updateClientPartial(@PathVariable("id") long id, @RequestBody Map<String, String> fields){
        clientService.updateClientPartial(id, fields);
        return new ResponseEntity<>("Client successfull updated" + fields, HttpStatus.OK);
    }
    @GetMapping(value = "/clients/{id)/orders")
    public ResponseEntity<Object> getAllOrders(@PathVariable("id") long id){
        Collection<Order> orders = clientService.getAllOrders(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


}
