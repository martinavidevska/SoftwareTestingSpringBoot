package database.project.carrental.E2ETests;

import database.project.carrental.model.Client;
import database.project.carrental.model.Renting;
import database.project.carrental.model.Vehicle;
import database.project.carrental.service.PaymentService;
import database.project.carrental.service.RentingService;
import database.project.carrental.web.controller.PaymentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.*;

@WebFluxTest(PaymentController.class)
public class PaymentControllerE2ETest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private RentingService rentingService;

    private Renting renting;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        renting = new Renting();
        renting.setId(1L);
        renting.setTotalAmount(100.00);
        Client client = new Client();
        client.setName("John Doe");
        renting.setClient(client);
        Vehicle vehicle = new Vehicle();
        vehicle.setModel("Toyota");
        renting.setVehicle(vehicle);

        when(rentingService.findById(1L)).thenReturn(renting);
    }

    @Test
    public void testPostPayment() {
        String requestBody = "cardNumber=4111111111111111&cvv=123&cardholderName=John Doe&expirationDate=12/24&rentingId=1";

        webTestClient.post()
                .uri("/payment") // Make sure this path is allowed in your security config
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isOk() // Expect a 200 OK response
                .expectBody(String.class).isEqualTo("rent-success"); // Expect the success view name

        verify(paymentService, times(1)).addPayment(1L, 100.00, renting.getClient(), renting.getVehicle(), "4111111111111111", "123", "12/24", "John Doe");
    }

}
