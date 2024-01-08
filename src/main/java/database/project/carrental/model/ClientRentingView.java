package database.project.carrental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ClientRentingView {
    @Id
    private Long idRenting;
    private String username;
    private LocalDate startRent;
    private LocalDate endRent;
    private String model;
    private String brand;
    private Double dailyPrice;
    private String description;
    private String addressPickedFrom;
    private String addressReturnedTo;
    private String pictureLink;
    private Double totalAmount;

}
