package database.project.carrental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AvailableVehicleView {
    @Id
    private String licensePlate;
    private String model;
    private String brand;
    private String description;
    private int seats;
    private Double dailyPrice;
    private int bags;
    private String pictureLink;


}
