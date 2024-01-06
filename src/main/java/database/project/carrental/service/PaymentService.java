package database.project.carrental.service;

import database.project.carrental.model.Client;
import database.project.carrental.model.Payment;
import database.project.carrental.model.Renting;
import database.project.carrental.model.Vehicle;

import java.time.LocalDate;

public interface PaymentService {
    Payment addPayment(Long rentingId, double amount, Client client, Vehicle vehicle, String cardNumber, String cvv, String expirationDate, String cardholderName);

}
