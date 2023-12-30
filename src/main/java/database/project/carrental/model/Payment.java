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
    @Column(name = "method_of_payment")
    private String methodeOfPayment;
    @ManyToOne
    @JoinColumn(name = "username")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "license_plate")
    private Vehicle vehicle;

    public Payment( Renting renting, Long amount, LocalDate dateOfPayment, String methodeOfPayment, Client client, Vehicle vehicle) {
        this.renting = renting;
        this.amount = amount;
        this.dateOfPayment = dateOfPayment;
        this.methodeOfPayment = methodeOfPayment;
        this.client = client;
        this.vehicle = vehicle;
    }

    public Payment() {

    }
}
