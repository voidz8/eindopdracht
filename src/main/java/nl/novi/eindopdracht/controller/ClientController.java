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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(value = "/clients/{companyName}")
    public ResponseEntity<Object> getClientByName(@PathVariable(value = "companyName") String companyName){
        return new ResponseEntity<>(clientService.getClientByName(companyName), HttpStatus.OK);
    }

    @DeleteMapping(value = "clients/{companyName}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "companyName") String companyName){
        clientService.deleteClient(companyName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<Object> createClient(@RequestBody Client client){
        String newName = clientService.createClient(client);
        return new ResponseEntity<>("A new client is created: "+ newName, HttpStatus.CREATED);
    }

    @PutMapping(value = "/clients/{companyname}")
    public ResponseEntity<Object> updateClient(@RequestParam(value = "company_name") String companyName, @RequestBody Client client){
        clientService.updateClient(companyName, client);
        return new ResponseEntity<>("Client with clientid: " + companyName+" is updated", HttpStatus.OK);
    }

    @PatchMapping(value = "/clients/{companyname}")
    public ResponseEntity<Object> updateClientPartial(@RequestParam(value = "company_name",required = false) String companyName, @RequestBody Map<String, String> fields){
        clientService.updateClientPartial(companyName, fields);
        return new ResponseEntity<>("Client " +companyName +" successfull updated" + fields, HttpStatus.OK);
    }
    @GetMapping(value = "/clients/{id)/orders")
    public ResponseEntity<Object> getAllOrders(@RequestParam(value = "company_name", required = false) String companyName){
        Collection<Order> orders = clientService.getAllOrders(companyName);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


}
