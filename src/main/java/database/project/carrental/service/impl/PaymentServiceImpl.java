package database.project.carrental.service.impl;

import database.project.carrental.model.Client;
import database.project.carrental.model.Payment;
import database.project.carrental.model.Renting;
import database.project.carrental.model.Vehicle;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.repository.PaymentRepository;
import database.project.carrental.repository.RentingRepository;
import database.project.carrental.repository.VehicleRepository;
import database.project.carrental.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final VehicleRepository vehicleRepository;
    private final RentingRepository rentingRepository;
    private final ClientRepository clientRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, VehicleRepository vehicleRepository, RentingRepository rentingRepository, ClientRepository clientRepository) {
        this.paymentRepository = paymentRepository;
        this.vehicleRepository = vehicleRepository;
        this.rentingRepository = rentingRepository;
        this.clientRepository = clientRepository;
    }
 //   public Payment(Renting renting, double amount, LocalDate dateOfPayment, Client client, Vehicle vehicle) {

    @Override
    public Payment addPayment(Long rentingId, double amount, Client client, Vehicle vehicle,String cardNumber, String cvv,String expirationDate, String cardholderName) {
        Renting renting=rentingRepository.findById(rentingId).orElseThrow();
        return paymentRepository.save(new Payment(renting,amount, LocalDate.now(), client,vehicle, cardNumber, cvv,expirationDate, cardholderName));
    }
}
