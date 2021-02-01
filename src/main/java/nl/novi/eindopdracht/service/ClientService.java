package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClientService {

    List<Client> getallClients();
    Client getClientById(long id);
    void deleteClient(long id);
    long createClient(Client client);
    void updateClient(long id, Client client);
    Optional<Client> getClientByCompanyname(String companyName);
    boolean clientExists(Long id);
    boolean clientExistsByName(String companyName);
    Order getAllOrders(long id, Order orders);
    void addOrder(Long id, Set<Order> orders);
}
