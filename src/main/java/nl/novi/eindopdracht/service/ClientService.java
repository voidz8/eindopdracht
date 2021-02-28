package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Client;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClientService {

    List<Client> getAllClients();
    Optional<Client> getClientByName(String companyName);
    void deleteClient(String companyName);
    String createClient(Client client);
    void updateClient(String companyName, Client client);
    void updateClientPartial(String companyName, Map<String, Object> fields);

}
