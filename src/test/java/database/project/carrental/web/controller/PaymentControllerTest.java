package database.project.carrental.web.controller;

import database.project.carrental.model.Client;
import database.project.carrental.model.Renting;
import database.project.carrental.model.Vehicle;
import database.project.carrental.service.PaymentService;
import database.project.carrental.service.RentingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private RentingService rentingService;

    @InjectMocks
    private PaymentController paymentController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(paymentController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testShowPaymentPage() throws Exception {
        Renting renting = new Renting();
        renting.setId(1L);

        when(rentingService.findById(1L)).thenReturn(renting);

        mockMvc.perform(get("/payment")
                        .param("rentingId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rent"))
                .andExpect(model().attribute("rent", renting))
                .andExpect(view().name("payment"));

        verify(rentingService, times(1)).findById(1L);
    }

    @Test
    public void testPostPayment() throws Exception {
        Renting renting = new Renting();
        renting.setId(1L);
        renting.setTotalAmount(100.0);
        Client client = new Client();
        Vehicle vehicle = new Vehicle();

        renting.setClient(client);
        renting.setVehicle(vehicle);

        when(rentingService.findById(1L)).thenReturn(renting);

        mockMvc.perform(post("/payment")
                        .param("cardNumber", "1234567890123456")
                        .param("cvv", "123")
                        .param("cardholderName", "John Doe")
                        .param("expirationDate", "12/25")
                        .param("rentingId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rent-success"));

        verify(rentingService, times(3)).findById(1L);
        verify(paymentService, times(1)).addPayment(
                1L,
                100.0,
                client,
                vehicle,
                "1234567890123456",
                "123",
                "12/25",
                "John Doe"
        );
    }
}
