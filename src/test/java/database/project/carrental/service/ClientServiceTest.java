package database.project.carrental.service;

import database.project.carrental.model.Client;
import database.project.carrental.model.Role;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client("testuser", "encodedpassword", "Test Name", "test@example.com", "1234567890", "D123456", "Test Address", Role.USER);
    }

    @Test
    public void testRegister() {
        when(passwordEncoder.encode("password")).thenReturn("encodedpassword");
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client registeredClient = clientService.register("testuser", "password", "Test Name", "test@example.com", "1234567890", "D123456", "Test Address");

        assertNotNull(registeredClient);
        assertEquals("testuser", registeredClient.getUsername());
        assertEquals("encodedpassword", registeredClient.getPassword());
        assertEquals(Role.USER, registeredClient.getRole());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testFindByUsername() {
        when(clientRepository.findByUsername("testuser")).thenReturn(Optional.of(client));

        Optional<Client> foundClient = clientService.findByUsername("testuser");

        assertTrue(foundClient.isPresent());
        assertEquals("testuser", foundClient.get().getUsername());
        verify(clientRepository, times(1)).findByUsername("testuser");
    }

    @Test
    public void testRegisterAdmin() {
        when(passwordEncoder.encode("adminpassword")).thenReturn("encodedadminpassword");
        Client adminClient = new Client("adminuser", "encodedadminpassword");
        when(clientRepository.save(any(Client.class))).thenReturn(adminClient);

        Client registeredAdmin = clientService.registerAdmin("adminuser", "adminpassword");

        assertNotNull(registeredAdmin);
        assertEquals("adminuser", registeredAdmin.getUsername());
        assertEquals("encodedadminpassword", registeredAdmin.getPassword());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testLoadUserByUsername_UserExists() {
        when(clientRepository.findByUsername("testuser")).thenReturn(Optional.of(client));

        UserDetails userDetails = clientService.loadUserByUsername("testuser");

        assertNotNull(userDetails);
        assertEquals("testuser", userDetails.getUsername());
        verify(clientRepository, times(1)).findByUsername("testuser");
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(clientRepository.findByUsername("unknownuser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> clientService.loadUserByUsername("unknownuser"));
        verify(clientRepository, times(1)).findByUsername("unknownuser");
    }
}