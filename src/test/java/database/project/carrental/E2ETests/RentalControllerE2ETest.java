package database.project.carrental.E2ETests;

import database.project.carrental.model.*;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.repository.LocationRepository;
import database.project.carrental.repository.RentingViewRepository;
import database.project.carrental.repository.VehicleRepository;
import database.project.carrental.service.RentingService;
import database.project.carrental.web.controller.RentingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureWebTestClient
@WebFluxTest(RentingController.class)
public class RentingControllerE2ETest {

    @Autowired
    private WebTestClient webTestClient;

    @Mock
    private RentingService rentingService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private RentingViewRepository rentingViewRepository;

    @InjectMocks
    private RentingController rentingController;

    private Vehicle vehicle;
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


        vehicle = new Vehicle();
        vehicle.setLicensePlate("ABC123");
        vehicle.setDailyPrice(50.0);
        vehicle.setVehicleType(new VehicleType(1L, "A regular car"));


        client = new Client();
        client.setUsername("john_doe");
        client.setName("John Doe");

        when(vehicleRepository.findByLicensePlate("ABC123")).thenReturn(vehicle);
        when(clientRepository.findByUsername("john_doe")).thenReturn(Optional.of(client));
    }

    @Test
    public void testAddRenting() {
        String licensePlate = "ABC123";
        Long pickedFromId = 1L;
        Long returnedToId = 2L;
        LocalDate startRent = LocalDate.now();
        LocalDate endRent = LocalDate.now().plusDays(2);

        Renting newRenting = new Renting();
        newRenting.setId(1L);
        when(rentingService.addRenting(any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(newRenting);


        webTestClient.post()
                .uri("/rent")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue("licensePlate=" + licensePlate +
                        "&pickedFromId=" + pickedFromId +
                        "&returnedToId=" + returnedToId +
                        "&startRent=" + startRent +
                        "&endRent=" + endRent)
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectHeader().valueEquals("Location", "/payment");

        verify(rentingService, times(1)).addRenting(
                eq(startRent),
                eq(endRent),
                anyDouble(),
                eq(vehicle),
                eq(client),
                eq(pickedFromId),
                eq(returnedToId)
        );
    }
}
