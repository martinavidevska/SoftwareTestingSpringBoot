package database.project.carrental;

import database.project.carrental.model.Vehicle;
import database.project.carrental.model.VehicleType;
import database.project.carrental.repository.*;
import database.project.carrental.service.AvailableVehicleViewService;
import database.project.carrental.service.RentingService;
import database.project.carrental.service.VehicleService;
import database.project.carrental.web.controller.VehicleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ActiveProfiles("test")
@WebMvcTest(controllers = VehicleController.class)
class CarRentalApplicationWebLayerTests {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @MockBean
    private LocationRepository locationRepository;

    @MockBean
    private VehicleTypeRepository vehicleTypeRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private RentingViewRepository rentingViewRepository;

    @MockBean
    private AvailableVehicleViewService availableVehicleViewService;

    @MockBean
    private RentingService rentingService;
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void TestVehicles() throws Exception {
        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vehicles"))
                .andExpect(model().attributeExists("vehicleTypes"))
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testListAll() throws Exception {
        // Arrange
        VehicleType vehicleType = new VehicleType("Sport");
        Vehicle vehicle1 = new Vehicle("SK0833JK", "Polo", "Volkswagen", 2, 2300.0, 2, vehicleType, "https://images.hindustantimes.com/auto/img/2022/04/08/1600x900/Volkswagen_Polo_Legend_edition_1649044005572_1649382819575.jpg");
        Vehicle vehicle2 = new Vehicle("OH0202DD", "Note", "Nissan", 5, 4500.0, 4, vehicleType, "https://wieck-nissanao-production.s3.amazonaws.com/photos/b58be6832baa579031319d5bfccf6b04f543d19c/preview-768x432.jpg");

        List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2);
        when(vehicleService.findByLicensePlate(vehicle1.getLicensePlate())).thenReturn(vehicle1);
     //   when(vehicleTypeRepository.findAll()).thenReturn(Arrays.asList(vehicleType));

        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vehicles"))
              //  .andExpect(model().attribute("vehicles", vehicles))
                .andExpect(view().name("index"));

        verify(vehicleService, times(1)).findAll();
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getAddForm_Success() throws Exception {
        List<VehicleType> vehicleTypes = this.vehicleTypeRepository.findAll();
        mockMvc.perform(get("/vehicles/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attribute("types", vehicleTypes))
                .andExpect(view().name("add-vehicle"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void addVehicle_Success() throws Exception {
        VehicleType vehicleType = new VehicleType("SUV");

        mockMvc.perform(MockMvcRequestBuilders.post("/vehicles/add-vehicle")
                        .param("licensePlate", "AB123CD")
                        .param("model", "Model S")
                        .param("brand", "Tesla")
                        .param("seats", "5")
                        .param("dailyPrice", "100.0")
                        .param("bags", "2")
                        .param("vehicleType", vehicleType.getDescription())
                        .param("pictureLink", "http://example.com/picture.jpg")
                        .with(csrf()))  // Add CSRF token
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/vehicles"));

        verify(vehicleService, times(1)).add(
                eq("AB123CD"),
                eq("Model S"),
                eq("Tesla"),
                eq(5),
                eq(100.0),
                eq(2),
                any(VehicleType.class),
                eq("http://example.com/picture.jpg")
        );
    }

}
