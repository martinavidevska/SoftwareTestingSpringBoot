package database.project.carrental.repository;

import database.project.carrental.model.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void testSaveLocation() {
        Location location = new Location();

        Location savedLocation = locationRepository.save(location);

        Optional<Location> retrievedLocation = locationRepository.findById(savedLocation.getId());

        assertTrue(retrievedLocation.isPresent());
        assertEquals(savedLocation.getId(), retrievedLocation.get().getId());
    }

    @Test
    public void testFindAllLocations() {
        Location location1 = new Location();
        Location location2 = new Location();
        locationRepository.save(location1);
        locationRepository.save(location2);

        List<Location> allLocations = locationRepository.findAll();

        assertEquals(2, allLocations.size());
    }
}
