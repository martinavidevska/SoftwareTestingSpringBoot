package database.project.carrental.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Renting renting;
    private Long amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfPayment;
    private String methodeOfPayment;
    @ManyToOne
    private Client client;
    @ManyToOne
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
