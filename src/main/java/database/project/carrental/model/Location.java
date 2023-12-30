package database.project.carrental.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="car_location")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_location")
    private Long id;
    private String name;
    private String address;

    public Location(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Location() {

    }
}
