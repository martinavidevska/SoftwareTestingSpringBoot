package database.project.carrental.service;

import database.project.carrental.model.Client;
import database.project.carrental.model.Role;
import database.project.carrental.model.exceptions.InvalidArgumentsException;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.service.AuthService;
import database.project.carrental.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client("testuser", "testpassword", "Test", "test@example.com", "1234567890", "SK4444AA", "Skopje", Role.USER);
    }

    @Test
    public void testLoginWithValidCredentials() {
        when(clientRepository.findByUsernameAndPassword("testuser", "testpassword")).thenReturn(Optional.of(client));

        Client result = authService.login("testuser", "testpassword");

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(clientRepository, times(1)).findByUsernameAndPassword("testuser", "testpassword");
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        when(clientRepository.findByUsernameAndPassword("invaliduser", "invalidpassword")).thenReturn(Optional.empty());

        assertThrows(InvalidArgumentsException.class, () -> {
            authService.login("invaliduser", "invalidpassword");
        });

        verify(clientRepository, times(1)).findByUsernameAndPassword("invaliduser", "invalidpassword");
    }

    @Test
    public void testLoginWithNullUsername() {
        assertThrows(InvalidArgumentsException.class, () -> {
            authService.login(null, "testpassword");
        });

        verify(clientRepository, never()).findByUsernameAndPassword(anyString(), anyString());
    }

    @Test
    public void testLoginWithNullPassword() {
        assertThrows(InvalidArgumentsException.class, () -> {
            authService.login("testuser", null);
        });

        verify(clientRepository, never()).findByUsernameAndPassword(anyString(), anyString());
    }

    @Test
    public void testLoginWithEmptyUsername() {
        assertThrows(InvalidArgumentsException.class, () -> {
            authService.login("", "testpassword");
        });

        verify(clientRepository, never()).findByUsernameAndPassword(anyString(), anyString());
    }

    @Test
    public void testLoginWithEmptyPassword() {
        assertThrows(InvalidArgumentsException.class, () -> {
            authService.login("testuser", "");
        });

        verify(clientRepository, never()).findByUsernameAndPassword(anyString(), anyString());
    }

    @Test
    public void testFindAll() {
        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<Client> result = authService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("testuser", result.get(0).getUsername());
        verify(clientRepository, times(1)).findAll();
    }
}
