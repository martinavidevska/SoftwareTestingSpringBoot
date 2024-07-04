package database.project.carrental;


import database.project.carrental.repository.*;
import database.project.carrental.service.RentingService;
import database.project.carrental.service.VehicleService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
class CarRentalApplicationIntegrationTests {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private RentingService rentingService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AvailableVehicleViewRepository availableVehicleViewRepository;

    @Autowired
    private RentingViewRepository rentingViewRepository;

    @Test
    void contextLoads() {
    }
}
