package database.project.carrental.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="vehicle")
public class Vehicle {
    @Id
    @JoinColumn(name = "license_plate")
    private String licensePlate;
    @JoinColumn(name = "daily_price")
    private Double dailyPrice;
    private String model;
    private String brand;
    private Integer seats;

    private Integer bags;
    @ManyToOne(cascade  = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicle_type")
    private VehicleType vehicleType;
    @JoinColumn(name="picture_link")
    private String pictureLink;

    public Vehicle(String licensePlate, String model, String brand, Integer seats, Double dailyPrice, Integer bags, VehicleType vehicleType, String pictureLink) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.seats = seats;
        this.dailyPrice = dailyPrice;
        this.bags = bags;
        this.vehicleType = vehicleType;
        this.pictureLink=pictureLink;
    }

    public Vehicle() {

    }
}
