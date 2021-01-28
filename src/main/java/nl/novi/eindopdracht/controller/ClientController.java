package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<Object> getClients(){
        List<Client> clients = clientService.getallClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping(value = "/client/{name}")
    public ResponseEntity<Object> getClientById(@PathVariable("id") long id){
        Client client = clientService.getClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);

    }
}
