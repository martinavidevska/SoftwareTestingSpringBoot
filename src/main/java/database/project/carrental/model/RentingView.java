package database.project.carrental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@Entity
public class RentingView {
    @Id
    private Long idRenting;
    private LocalDate startRent;
    private LocalDate endRent;
    private String nameClient;
    private String licensePlate;
    private String model;
    private String brand;
    private String pickedFrom;
    private String returnedTo;
    private Double totalAmount;
    private String cardholderName;
}
