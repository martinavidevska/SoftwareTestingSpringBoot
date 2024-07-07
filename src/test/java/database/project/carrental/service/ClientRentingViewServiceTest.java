package database.project.carrental.service;

import database.project.carrental.model.ClientRentingView;
import database.project.carrental.repository.ClientRentingViewRepository;
import database.project.carrental.service.ClientRentingViewService;
import database.project.carrental.service.impl.ClientRentingViewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientRentingViewServiceTest {

    @Mock
    private ClientRentingViewRepository clientRentingViewRepository;

    @InjectMocks
    private ClientRentingViewServiceImpl clientRentingViewService;

    private ClientRentingView clientRentingView;

    @BeforeEach
    public void setUp() {
        clientRentingView = new ClientRentingView();
    }

    @Test
    public void testFindByUsername() {
        String username = "testuser";
        when(clientRentingViewRepository.findAllByUsername(username)).thenReturn(List.of(clientRentingView));

        List<ClientRentingView> result = clientRentingViewService.findByUsername(username);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(clientRentingView, result.get(0));
        verify(clientRentingViewRepository, times(1)).findAllByUsername(username);
    }

    @Test
    public void testFindByUsernameReturnsEmptyList() {
        String username = "testuser";
        when(clientRentingViewRepository.findAllByUsername(username)).thenReturn(List.of());

        List<ClientRentingView> result = clientRentingViewService.findByUsername(username);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(clientRentingViewRepository, times(1)).findAllByUsername(username);
    }

    @Test
    public void testFindAll() {
        when(clientRentingViewRepository.findAll()).thenReturn(List.of(clientRentingView));

        List<ClientRentingView> result = clientRentingViewService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(clientRentingView, result.get(0));
        verify(clientRentingViewRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllReturnsEmptyList() {
        when(clientRentingViewRepository.findAll()).thenReturn(List.of());

        List<ClientRentingView> result = clientRentingViewService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(clientRentingViewRepository, times(1)).findAll();
    }
}
