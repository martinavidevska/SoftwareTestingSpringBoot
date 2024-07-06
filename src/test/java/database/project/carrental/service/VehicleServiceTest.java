package database.project.carrental.service;

import database.project.carrental.model.Vehicle;
import database.project.carrental.model.VehicleType;
import database.project.carrental.repository.VehicleRepository;
import database.project.carrental.repository.VehicleTypeRepository;
import database.project.carrental.service.VehicleService;
import database.project.carrental.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleTypeRepository vehicleTypeRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private VehicleType vehicleType;

    @BeforeEach
    public void setUp() {
        vehicleType = new VehicleType();
        vehicleType.setDescription("SUV");

        vehicle1 = new Vehicle("SK0833JK", "Polo", "Volkswagen", 2, 2300.0, 2, vehicleType, "https://images.hindustantimes.com/auto/img/2022/04/08/1600x900/Volkswagen_Polo_Legend_edition_1649044005572_1649382819575.jpg");
        vehicle2 = new Vehicle("OH0202DD", "Note", "Nissan", 5, 4500.0, 4, vehicleType, "https://wieck-nissanao-production.s3.amazonaws.com/photos/b58be6832baa579031319d5bfccf6b04f543d19c/preview-768x432.jpg");
    }

    @Test
    public void testFindByLicensePlate() {
        when(vehicleRepository.findByLicensePlate("SK0833JK")).thenReturn(vehicle1);

        Vehicle result = vehicleService.findByLicensePlate("SK0833JK");

        assertEquals(vehicle1, result);
        verify(vehicleRepository, times(1)).findByLicensePlate("SK0833JK");
    }

    @Test
    public void testRemove() {
        when(vehicleRepository.findByLicensePlate("SK0833JK")).thenReturn(vehicle1);
        doNothing().when(vehicleRepository).delete(vehicle1);

        Vehicle result = vehicleService.remove("SK0833JK");

        assertEquals(vehicle1, result);
        verify(vehicleRepository, times(1)).findByLicensePlate("SK0833JK");
        verify(vehicleRepository, times(1)).delete(vehicle1);
    }

    @Test
    public void testAdd() {
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle1);

        Vehicle result = vehicleService.add("SK0833JK", "Polo", "Volkswagen", 2, 2300.0, 2, vehicleType, "https://images.hindustantimes.com/auto/img/2022/04/08/1600x900/Volkswagen_Polo_Legend_edition_1649044005572_1649382819575.jpg");

        assertEquals(vehicle1, result);
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    }

    @Test
    public void testFindAll() {
        when(vehicleRepository.findAll()).thenReturn(Arrays.asList(vehicle1, vehicle2));

        List<Vehicle> result = vehicleService.findAll();

        assertEquals(2, result.size());
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    public void testFilter() {
        when(vehicleTypeRepository.findByDescription("SUV")).thenReturn(Optional.of(vehicleType));
        when(vehicleRepository.findAllByVehicleTypeAndDailyPriceIsLessThanEqualAndModel(vehicleType, 2500.0, "Polo")).thenReturn(Arrays.asList(vehicle1));

        List<Vehicle> result = vehicleService.filter("SUV", "2500", "Polo");

        assertEquals(1, result.size());
        verify(vehicleTypeRepository, times(1)).findByDescription("SUV");
        verify(vehicleRepository, times(1)).findAllByVehicleTypeAndDailyPriceIsLessThanEqualAndModel(vehicleType, 2500.0, "Polo");
    }
}
