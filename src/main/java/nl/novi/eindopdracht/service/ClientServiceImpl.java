package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.ClientNotFoundException;
import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getallClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public long createClient(Client client) {
        Client newClient = clientRepository.save(client);
        return newClient.getId();
    }

    @Override
    public void updateClient(long id, Client client) {
        Client existingClient = clientRepository.findById(id).orElse(null);
        existingClient.setCompanyName(client.getCompanyName());
        existingClient.setDebtorNumber(client.getDebtorNumber());
        existingClient.setEmail(client.getEmail());
        clientRepository.save(existingClient);
    }

    @Override
    public Optional<Client> getClientByCompanyname(String companyName) {
        return clientRepository.findByCompanyName(companyName);
    }

    @Override
    public boolean clientExists(Long id) {
        return clientRepository.existsById(id);
    }

    @Override
    public boolean clientExistsByName(String companyName) {
        return clientRepository.findByCompanyName(companyName).get() != null;
    }

    @Override
    public Order getAllOrders(long id, Order orders) {
        clientRepository.findById(id);
        return orders;
    }


    @Override
    public void addOrder(Long id, Set<Order> orders) {
        if (!clientRepository.existsById(id)) {throw new ClientNotFoundException();}
        Client client = clientRepository.findById(id).get();
        client.setOrders(orders);
        clientRepository.save(client);
    }


}
