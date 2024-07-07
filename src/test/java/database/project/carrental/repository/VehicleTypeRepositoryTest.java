package database.project.carrental.repository;

import database.project.carrental.model.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class VehicleTypeRepositoryTest {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    private VehicleType sedanType;

    @BeforeEach
    public void setUp() {
        sedanType = new VehicleType("SEDAN");
        vehicleTypeRepository.save(sedanType);
    }

    @Test
    public void testFindById() {
        Optional<VehicleType> found = vehicleTypeRepository.findById(sedanType.getId());
        assertTrue(found.isPresent());
        assertEquals("SEDAN", found.get().getDescription());
    }

    @Test
    public void testFindByDescription() {
        Optional<VehicleType> found = vehicleTypeRepository.findByDescription("SEDAN");
        assertTrue(found.isPresent());
        assertEquals(sedanType.getId(), found.get().getId());
    }

    @Test
    public void testSave() {
        VehicleType suvType = new VehicleType("SUV");
        vehicleTypeRepository.save(suvType);

        Optional<VehicleType> found = vehicleTypeRepository.findById(suvType.getId());
        assertTrue(found.isPresent());
        assertEquals("SUV", found.get().getDescription());
    }

    @Test
    public void testDelete() {
        vehicleTypeRepository.delete(sedanType);

        Optional<VehicleType> found = vehicleTypeRepository.findById(sedanType.getId());
        assertTrue(found.isEmpty());
    }
}
