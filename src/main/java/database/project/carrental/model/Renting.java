package database.project.carrental.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name="renting")
public class Renting {

    @Id
    @Column(name = "id_renting")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_rent", nullable = false)
    private LocalDate startRent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_rent")
    private LocalDate endRent;

    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "license_plate")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "username")
    private Client client;

    //@ManyToOne
   // @JoinColumn(name = "id_employee")
  //  private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_location_picked_from")
    private Location pickedFrom;

    @ManyToOne
    @JoinColumn(name = "id_location_returned_to")
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
