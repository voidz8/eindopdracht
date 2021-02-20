package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Order;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface ClientService {

    List<Client> getallClients();
    Optional<Client> getClientByName(String companyName);
    void deleteClient(String companyName);
    String createClient(Client client);
    void updateClient(String companyName, Client client);
    void updateClientPartial(String companyName, String email, Long debtorNumber, Set<Order> orders,Client client);
    boolean clientExistsByName(String companyName);
    Collection<Order> getAllOrders(String companyName);
}
