package nl.novi.eindopdracht.testservice;

import nl.novi.eindopdracht.EindopdrachtApplication;
import nl.novi.eindopdracht.exceptions.ClientNotFoundException;
import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.repository.ClientRepository;
import nl.novi.eindopdracht.service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest()
@ContextConfiguration(classes = {EindopdrachtApplication.class})
public class TestClientService {

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Mock
    Client client;

    @Test
    public void testGetClients() {
        when(clientRepository.findAll()).thenReturn(Stream
                .of(new Client("novi", "info@novi.nl", 111L), new Client("Hsproxies", "hsproxies@gmail.com", 123465L)).collect(Collectors.toList()));
        assertEquals(2, clientService.getAllClients().size());
    }

    @Test
    public void testCreateClient() {
        client = new Client("novi", "info@novi.nl", 111L);
        when(clientRepository.save(client)).thenReturn(client);
        assertEquals(client.getCompanyName(), clientService.createClient(client));
    }

   /*@Test
    public void testGetClient() {
       client = new Client("novi", "info@novi.nl", 111L);
       String name1 = client.getCompanyName();
        Mockito
        .when(clientRepository.findByCompanyName(name1))
                .thenReturn(Optional.ofNullable(client));

        String name = "novi";
        String expected = "info@novi.nl";

        Optional<Client> found = clientService.getClientByName(name);

        assertEquals(expected, found.get().getEmail());
    }*/


    @Test
    public void testDeleteClient(){
        Client client = new Client("novi", "info@novi.nl",111L);
        clientRepository.deleteById(client.getCompanyName());
        verify(clientRepository, times(1)).deleteById(client.getCompanyName());
    }

   /* @Test
    public void testUpdateClient(){
        Client client = new Client("novi", "info@novi.nl",111L);
        when(clientService.updateClient(client.getCompanyName(), client)).thenReturn(client);
        mvc.perform(put("/clients/") + client.getCompanyName()).
    }*/
}
