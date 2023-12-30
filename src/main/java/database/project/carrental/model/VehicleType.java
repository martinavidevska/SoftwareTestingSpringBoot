package database.project.carrental.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="vehicle_type")
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    public VehicleType(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public VehicleType() {
    }
}
