package database.project.carrental.service;

import database.project.carrental.model.*;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.repository.PaymentRepository;
import database.project.carrental.repository.RentingRepository;
import database.project.carrental.repository.VehicleRepository;
import database.project.carrental.service.PaymentService;
import database.project.carrental.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private RentingRepository rentingRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Renting renting;
    private Client client;
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        renting = new Renting();
        client = new Client();
        vehicle = new Vehicle();
    }

    @Test
    public void testAddPayment() {
        Long rentingId = 1L;
        double amount = 100.0;
        String cardNumber = "1234567812345678";
        String cvv = "123";
        String expirationDate = "12/25";
        String cardholderName = "John Doe";

        when(rentingRepository.findById(rentingId)).thenReturn(Optional.of(renting));
        when(paymentRepository.save(any(Payment.class))).thenReturn(new Payment());

        Payment payment = paymentService.addPayment(rentingId, amount, client, vehicle, cardNumber, cvv, expirationDate, cardholderName);

        assertNotNull(payment);
        verify(rentingRepository, times(1)).findById(rentingId);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }
}
