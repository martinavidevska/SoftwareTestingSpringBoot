package database.project.carrental.service;

import database.project.carrental.model.Location;
import database.project.carrental.repository.LocationRepository;
import database.project.carrental.service.LocationService;
import database.project.carrental.service.impl.LocationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    private List<Location> locationList;

    @BeforeEach
    public void setUp() {
        Location location1 = new Location(1L,"Dracevo","ul.Ruzveltova br.10");
        Location location2 = new Location(2L,"ABC","Centar");
        locationList = Arrays.asList(location1, location2);
    }

    @Test
    public void testListAll() {
        when(locationRepository.findAll()).thenReturn(locationList);

        List<Location> result = locationService.listAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(locationRepository, times(1)).findAll();
    }
}
