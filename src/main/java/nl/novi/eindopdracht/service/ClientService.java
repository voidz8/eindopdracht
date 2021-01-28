package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Client;

import java.util.Collection;
import java.util.List;

public interface ClientService {

    List<Client> getallClients();
    Client getClientById(long id);

}
