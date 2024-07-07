package database.project.carrental.repository;

import database.project.carrental.model.Client;
import database.project.carrental.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testSaveClient() {
        Client client = new Client("username", "password", "Name", "email@example.com", "1234567890", "license", "address", Role.USER);

        Client savedClient = clientRepository.save(client);

        Optional<Client> retrievedClient = clientRepository.findByUsername(savedClient.getUsername());

        assertTrue(retrievedClient.isPresent());
        assertEquals(savedClient.getUsername(), retrievedClient.get().getUsername());
        assertEquals(savedClient.getEmail(), retrievedClient.get().getEmail());
        assertEquals(savedClient.getRole(), retrievedClient.get().getRole());
    }

    @Test
    public void testFindByUsername() {
        Client client = new Client("username", "password", "Name", "email@example.com", "1234567890", "license", "address", Role.USER);
        clientRepository.save(client);

        Optional<Client> retrievedClient = clientRepository.findByUsername("username");

        assertTrue(retrievedClient.isPresent());
        assertEquals(client.getUsername(), retrievedClient.get().getUsername());
        assertEquals(client.getEmail(), retrievedClient.get().getEmail());
        assertEquals(client.getRole(), retrievedClient.get().getRole());
    }

    @Test
    public void testFindByEmail() {
        Client client = new Client("username", "password", "Name", "email@example.com", "1234567890", "license", "address", Role.USER);
        clientRepository.save(client);

        Client retrievedClient = clientRepository.findByEmail("email@example.com");

        assertEquals(client.getEmail(), retrievedClient.getEmail());
    }

    @Test
    public void testFindByUsernameAndPassword() {
        Client client = new Client("username", "password", "Name", "email@example.com", "1234567890", "license", "address", Role.USER);
        clientRepository.save(client);

        Optional<Client> retrievedClient = clientRepository.findByUsernameAndPassword("username", "password");

        assertTrue(retrievedClient.isPresent());
        assertEquals(client.getUsername(), retrievedClient.get().getUsername());
        assertEquals(client.getPassword(), retrievedClient.get().getPassword());
        assertEquals(client.getRole(), retrievedClient.get().getRole());
    }
}
