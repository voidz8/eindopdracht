package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.ClientNotFoundException;
import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }


    @Override
    public Optional<Client> getClientByName(String companyName) {
        if(!clientRepository.existsClientByCompanyName(companyName)) {throw new ClientNotFoundException();}
        return clientRepository.findByCompanyName(companyName);
    }

    @Override
    public void deleteClient(String companyName) {
        if(!clientRepository.existsClientByCompanyName(companyName)){throw new ClientNotFoundException();}
        clientRepository.deleteById(companyName);
    }

    @Override
    public String createClient(Client client) {
        Client newClient = clientRepository.save(client);
        return newClient.getCompanyName();
    }

    @Override
    public void updateClient(String companyName, Client client) {
        if (!clientRepository.existsClientByCompanyName(companyName)){throw new ClientNotFoundException();}
        Client existingClient = clientRepository.findByCompanyName(companyName).get();
        existingClient.setCompanyName(client.getCompanyName());
        existingClient.setDebtorNumber(client.getDebtorNumber());
        existingClient.setEmail(client.getEmail());
        clientRepository.save(existingClient);
    }

    @Override
    public void updateClientPartial(String companyName, Map<String, Object> fields) {
        if(!clientRepository.existsClientByCompanyName(companyName)) {throw new ClientNotFoundException();}
        Client client = clientRepository.findByCompanyName(companyName).get();
        for (String field : fields.keySet()){
            switch (field){
                case "email":
                    client.setEmail((String) fields.get(field));
                    break;
                case "debtorNumber":
                    client.setDebtorNumber((Integer) fields.get(field));
                    break;
            }
        }
        clientRepository.save(client);
    }
}
