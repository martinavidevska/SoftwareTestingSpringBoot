package database.project.carrental.repository;

import database.project.carrental.model.Vehicle;
import database.project.carrental.model.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    private Vehicle vehicle;
    private VehicleType sedanType;

    @BeforeEach
    public void setUp() {
        sedanType = new VehicleType("SEDAN");
        vehicleTypeRepository.save(sedanType);

        vehicle = new Vehicle();
        vehicle.setLicensePlate("ABC123");
        vehicle.setModel("Model S");
        vehicle.setSeats(5);
        vehicle.setDailyPrice(100.0);
        vehicle.setVehicleType(sedanType);

        vehicleRepository.save(vehicle);
    }

    @Test
    public void testFindByLicensePlate() {
        Vehicle found = vehicleRepository.findByLicensePlate("ABC123");
        assertNotNull(found);
        assertEquals("Model S", found.getModel());
    }

    @Test
    public void testFindAllByVehicleType() {
        List<Vehicle> found = vehicleRepository.findAllByVehicleType(sedanType);
        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals("Model S", found.get(0).getModel());
    }

    @Test
    public void testFindByModel() {
        Vehicle found = vehicleRepository.findByModel("Model S");
        assertNotNull(found);
        assertEquals("ABC123", found.getLicensePlate());
    }

    @Test
    public void testFindBySeats() {
        Vehicle found = vehicleRepository.findBySeats(5);
        assertNotNull(found);
        assertEquals("Model S", found.getModel());
    }

    @Test
    public void testFindAllByModel() {
        List<Vehicle> found = vehicleRepository.findAllByModel("Model S");
        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals("ABC123", found.get(0).getLicensePlate());
    }
}
