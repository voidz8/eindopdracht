package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.service.ClientService;
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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ClientController implements Serializable {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<Object> getClients(){
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
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
        String newClient = clientService.createClient(client);
        return new ResponseEntity<>("A new client is created: "+ newClient, HttpStatus.CREATED);
    }

    @PutMapping(value = "/clients/{companyName}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "companyName") String companyName, @RequestBody Client client){
        clientService.updateClient(companyName, client);
        return new ResponseEntity<>("Client: " + companyName+" is updated.", HttpStatus.OK);
    }

    @PatchMapping(value = "/clients/{companyName}")
    public ResponseEntity<Object> updateClientPartial(@PathVariable(value = "companyName") String companyName,@RequestBody Map<String, Object> fields){
        clientService.updateClientPartial(companyName, fields);
        return new ResponseEntity<>("Client " +companyName +" is updated." , HttpStatus.OK);
    }
    @GetMapping(value = "/clients/{companyName)/orders")
    public ResponseEntity<Object> getAllOrders(@RequestParam(value = "companyName") String companyName){
        return new ResponseEntity<>(clientService.getAllOrders(companyName), HttpStatus.OK);
    }

}
