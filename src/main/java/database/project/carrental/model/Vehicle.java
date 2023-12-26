package database.project.carrental.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Vehicle {
    @Id
    private String licensePlate;
    private String model;
    private String brand;
    private Integer seats;
    private Double dailyPrice;
    private Integer bags;
    @ManyToOne(cascade  = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private VehicleType vehicleType;

    public Vehicle(String licensePlate, String model, String brand, Integer seats, Double dailyPrice, Integer bags, VehicleType vehicleType) {
        licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.seats = seats;
        this.dailyPrice = dailyPrice;
        this.bags = bags;
        this.vehicleType = vehicleType;
    }

    public Vehicle() {

    }
}
