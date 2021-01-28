package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
}
