package database.project.carrental.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String contact;
    private String address;

    public Location(Long id, String contact, String address) {
        this.id = id;
        this.contact = contact;
        this.address = address;
    }

    public Location() {

    }
}
