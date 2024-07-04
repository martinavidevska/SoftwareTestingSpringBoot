package database.project.carrental.web.controller;

import ch.qos.logback.core.pattern.Converter;
import database.project.carrental.model.*;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.repository.LocationRepository;
import database.project.carrental.repository.RentingViewRepository;
import database.project.carrental.repository.VehicleRepository;
import database.project.carrental.service.RentingService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//
//@ActiveProfiles("test")
//@WebMvcTest(controllers = RentingController.class)
class RentingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RentingService rentingService;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private RentingViewRepository rentingViewRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private RentingController rentingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(rentingController)
                .setViewResolvers(viewResolver)
                .build();
    }
    @Test
    @WithMockUser(username = "tina", roles = {"USER"})
    void testAddRenting() throws Exception {
        Client client = new Client();
        client.setUsername("tina");
        clientRepository.save(client);

        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("AB123CD");
        vehicle.setDailyPrice(100.0);

        Location pickedFrom = new Location(1L, "Skopje", "Skopje");
        Location returnedTo = new Location(2L, "Bitola", "Bitola");

        when(clientRepository.findByUsername("tina")).thenReturn(Optional.of(client));
        when(vehicleRepository.findByLicensePlate("AB123CD")).thenReturn(vehicle);
        when(rentingService.addRenting(any(), any(), anyDouble(), any(), any(), any(), any())).thenReturn(new Renting());

        mockMvc.perform(post("/rent")
                        .param("licensePlate", "AB123CD")
                        .param("pickedFromId", "1")
                        .param("returnedToId", "2")
                        .param("startRent", "2024-07-04")
                        .param("endRent", "2024-07-05")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/payment"));
    }


@Test
    void testShowVehicleById() throws Exception {
        Vehicle vehicle = new Vehicle();
        VehicleType vehicleType = new VehicleType();
        vehicleType.setDescription("SUV");
        vehicle.setVehicleType(vehicleType);

        when(vehicleRepository.findByLicensePlate("AB123CD")).thenReturn(vehicle);
        when(locationRepository.findAll()).thenReturn(Collections.singletonList(new Location()));

        mockMvc.perform(get("/rent/AB123CD"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vehicle"))
                .andExpect(model().attributeExists("vehicleType"))
                .andExpect(model().attributeExists("locations"))
                .andExpect(view().name("rent"));
    }

    @Test
    void testListAll() throws Exception {
        when(rentingViewRepository.findAll()).thenReturn(Collections.singletonList(new RentingView()));

        mockMvc.perform(get("/listAll"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rentings"))
                .andExpect(view().name("rentings"));
    }
}
