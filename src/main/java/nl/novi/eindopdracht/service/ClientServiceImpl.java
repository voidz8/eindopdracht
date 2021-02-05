package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.ClientNotFoundException;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
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
    public Optional<Client> getClientByName(String companyName) {
        if(!clientRepository.existsClientByCompanyName(companyName)) throw new ClientNotFoundException();
        return clientRepository.findByCompanyName(companyName);
    }

    @Override
    public void deleteClient(String companyName) {
        clientRepository.deleteClientByCompanyName(companyName);
    }

    @Override
    public String createClient(Client client) {
        Client newClient = clientRepository.save(client);
        return newClient.getCompanyName();
    }

    @Override
    public void updateClient(String companyName, Client client) {
        Client existingClient = clientRepository.findByCompanyName(companyName).orElse(null);
        existingClient.setDebtorNumber(client.getDebtorNumber());
        existingClient.setEmail(client.getEmail());
        clientRepository.save(existingClient);
    }

    @Override
    public void updateClientPartial(String companyName, Map<String, String> fields) {
        if(!clientRepository.existsClientByCompanyName(companyName)) {throw new ClientNotFoundException();}
        Client storedClient = clientRepository.findByCompanyName(companyName).orElse(null);
        for(String field : fields.keySet()){
            switch (field){
                case "email":
                    storedClient.setEmail((String) fields.get(field));
                    break;
                case "debtorNumber":
                    storedClient.setDebtorNumber((String) fields.get(field));
                    break;
            }
        }
        clientRepository.save(storedClient);
    }

    @Override
    public boolean clientExistsByName(String companyName){
        return clientRepository.findByCompanyName(companyName).get() != null;
    }


    @Override
    public Collection<Order> getAllOrders(String companyName) {
        if(!clientRepository.existsClientByCompanyName(companyName)){throw new ClientNotFoundException();}
        Optional<Client> client = clientRepository.findByCompanyName(companyName);
        return client.get().getOrders();
    }


    @Override
    public void addOrder(String companyName, Set<Order> orders) {
        if (!clientRepository.existsClientByCompanyName(companyName)) {throw new ClientNotFoundException();}
        Client client = clientRepository.findByCompanyName(companyName).get();
        client.setOrders(orders);
        clientRepository.save(client);
    }


}
