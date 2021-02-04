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
    public Optional<Client> getClientById(long id){
        if(!clientRepository.existsById(id)) {throw new ClientNotFoundException();}
        return clientRepository.findById(id);
    }

    @Override
    public Optional<Client> getClientByName(String companyName) {
        if(!clientRepository.existsClientByCompanyName(companyName)) throw new ClientNotFoundException();
        return clientRepository.findByCompanyName(companyName);
    }

    @Override
    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public String createClient(Client client) {
        Client newClient = clientRepository.save(client);
        return newClient.getCompanyName();
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
    public void updateClientPartial(long id, Map<String, String> fields) {
        if(!clientRepository.existsById(id)) {throw new ClientNotFoundException();}
        Client storedClient = clientRepository.findById(id).orElse(null);
        for(String field : fields.keySet()){
            switch (field){
                case "companyName":
                    storedClient.setCompanyName((String) fields.get(field));
                    break;
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
    public boolean clientExistsById(long id) {
        return clientRepository.findById(id).get() != null;
    }

    @Override
    public boolean clientExistsByName(String companyName){
        return clientRepository.findByCompanyName(companyName).get() != null;
    }


    @Override
    public Collection<Order> getAllOrders(long id) {
        if(!clientRepository.existsById(id)){throw new ClientNotFoundException();}
        Optional<Client> client = clientRepository.findById(id);
        return client.get().getOrders();
    }


    @Override
    public void addOrder(long id, Set<Order> orders) {
        if (!clientRepository.existsById(id)) {throw new ClientNotFoundException();}
        Client client = clientRepository.findById(id).get();
        client.setOrders(orders);
        clientRepository.save(client);
    }


}
