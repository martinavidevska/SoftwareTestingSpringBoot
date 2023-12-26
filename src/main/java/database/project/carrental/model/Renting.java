package database.project.carrental.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
public class Renting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startRent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endRent;
    private double totalAmount;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Client client;
    //Mozebi posle koga ke stavam i about us page
    //@ManyToOne
    //private Employee employee;
    @ManyToOne
    private Location pickedFrom;
    @ManyToOne
    private Location returnedTo;

    public Renting(LocalDate startRent, LocalDate endRent, double totalAmount, Vehicle vehicle, Client client,  Location pickedFrom, Location returnedTo) {
        this.startRent = startRent;
        this.endRent = endRent;
        this.totalAmount = totalAmount;
        this.vehicle = vehicle;
        this.client = client;
        this.pickedFrom = pickedFrom;
        this.returnedTo = returnedTo;
    }

    public Renting() {

    }
}
