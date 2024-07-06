package database.project.carrental.service;

import database.project.carrental.model.*;
import database.project.carrental.model.exceptions.RentingNotFoundException;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.repository.LocationRepository;
import database.project.carrental.repository.RentingRepository;
import database.project.carrental.service.RentingService;
import database.project.carrental.service.impl.RentingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentingServiceTest {

    @Mock
    private RentingRepository rentingRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private RentingServiceImpl rentingService;

    private Renting renting;
    private Client client;
    private Vehicle vehicle;
    private Location pickedFrom;
    private Location returnedTo;

    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setUsername("testuser");

        vehicle = new Vehicle();
        vehicle.setLicensePlate("SK0833JK");

        pickedFrom = new Location();
        pickedFrom.setId(1L);

        returnedTo = new Location();
        returnedTo.setId(2L);

        renting = new Renting(LocalDate.now(), LocalDate.now().plusDays(5), 500.0, vehicle, client, pickedFrom, returnedTo);
        renting.setId(1L);
    }

    @Test
    public void testAddRenting() {
        when(locationRepository.findById(1L)).thenReturn(Optional.of(pickedFrom));
        when(locationRepository.findById(2L)).thenReturn(Optional.of(returnedTo));
        when(rentingRepository.save(any(Renting.class))).thenReturn(renting);

        Renting result = rentingService.addRenting(LocalDate.now(), LocalDate.now().plusDays(5), 500.0, vehicle, client, 1L, 2L);

        assertEquals(renting, result);
        verify(locationRepository, times(1)).findById(1L);
        verify(locationRepository, times(1)).findById(2L);
        verify(rentingRepository, times(1)).save(any(Renting.class));
    }

    @Test
    public void testFindById() {
        when(rentingRepository.findById(1L)).thenReturn(Optional.of(renting));

        Renting result = rentingService.findById(1L);

        assertEquals(renting, result);
        verify(rentingRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAllByUsername() {
        when(clientRepository.findByUsername("testuser")).thenReturn(Optional.of(client));
        when(rentingRepository.findAllByClient(Optional.of(client))).thenReturn(Arrays.asList(renting));

        List<Renting> result = rentingService.findAllByUsername("testuser");

        assertEquals(1, result.size());
        assertEquals(renting, result.get(0));
        verify(clientRepository, times(1)).findByUsername("testuser");
        verify(rentingRepository, times(1)).findAllByClient(Optional.of(client));
    }

    @Test
    public void testFindByIdNotFound() {
        when(rentingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RentingNotFoundException.class, () -> rentingService.findById(1L));
        verify(rentingRepository, times(1)).findById(1L);
    }
}
