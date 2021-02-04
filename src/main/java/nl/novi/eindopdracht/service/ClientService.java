package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface ClientService {

    List<Client> getallClients();
    Optional<Client> getClientById(long id);
    Optional<Client> getClientByName(String companyName);
    void deleteClient(long id);
    String createClient(Client client);
    void updateClient(long id, Client client);
    void updateClientPartial(long id, Map<String, String> fields);
    boolean clientExistsById(long id);
    boolean clientExistsByName(String companyName);

    Order getAllOrders(String companyName, Order orders);
    void addOrder(long id, Set<Order> orders);
}
