package database.project.carrental.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_renting")
    private Renting renting;
    private double amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfPayment;
    @ManyToOne
    @JoinColumn(name = "username")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "license_plate")
    private Vehicle vehicle;
    private String cardNumber;
    private String ccv;
    private String expirationDate;
    private String cardholderName;

    public Payment(Renting renting, double amount, LocalDate dateOfPayment, Client client, Vehicle vehicle, String cardNumber, String ccv, String expirationDate, String cardholderName) {
        this.renting = renting;
        this.amount = amount;
        this.dateOfPayment = dateOfPayment;
        this.client = client;
        this.vehicle = vehicle;
        this.cardNumber=cardNumber;
        this.ccv=ccv;
        this.expirationDate=expirationDate;
        this.cardholderName=cardholderName;
    }

    public Payment() {

    }
}
