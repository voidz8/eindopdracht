package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Client;

import java.util.Collection;
import java.util.List;

public interface ClientService {

    List<Client> getallClients();
    Client getClientById(long id);
    void deleteClient(long id);
    long createClient(Client client);
    void updateClient(long id, Client client);
}
