package database.project.carrental.service;

import database.project.carrental.model.AvailableVehicleView;
import database.project.carrental.repository.AvailableVehicleViewRepository;
import database.project.carrental.service.impl.AvailableVehicleViewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AvailableVehicleViewServiceTest {

    @Mock
    private AvailableVehicleViewRepository availableVehicleViewRepository;

    @InjectMocks
    private AvailableVehicleViewServiceImpl availableVehicleViewService;

    private AvailableVehicleView vehicle;

    @BeforeEach
    public void setUp() {
        vehicle = new AvailableVehicleView();
    }

    @Test
    public void testGetAvailableVehicles() {
        when(availableVehicleViewRepository.findAll()).thenReturn(List.of(vehicle));

        List<AvailableVehicleView> result = availableVehicleViewService.getAvailableVehicles();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehicle, result.get(0));
        verify(availableVehicleViewRepository, times(1)).findAll();
    }

    @Test
    public void testFilterWithAllParameters() {
        String description = "Test Description";
        String model = "Test Model";
        String dailyPrice = "50";
        when(availableVehicleViewRepository.findByDescriptionAndDailyPriceIsLessThanEqualAndModel(description, Double.parseDouble(dailyPrice), model))
                .thenReturn(List.of(vehicle));

        List<AvailableVehicleView> result = availableVehicleViewService.filter(description, model, dailyPrice);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehicle, result.get(0));
        verify(availableVehicleViewRepository, times(1)).findByDescriptionAndDailyPriceIsLessThanEqualAndModel(description, Double.parseDouble(dailyPrice), model);
    }

    @Test
    public void testFilterWithDescriptionAndDailyPrice() {
        String description = "Test Description";
        String dailyPrice = "50";
        when(availableVehicleViewRepository.findByDescriptionAndDailyPriceIsLessThanEqual(description, Double.parseDouble(dailyPrice)))
                .thenReturn(List.of(vehicle));

        List<AvailableVehicleView> result = availableVehicleViewService.filter(description, null, dailyPrice);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehicle, result.get(0));
        verify(availableVehicleViewRepository, times(1)).findByDescriptionAndDailyPriceIsLessThanEqual(description, Double.parseDouble(dailyPrice));
    }

    @Test
    public void testFilterWithDescriptionAndModel() {
        String description = "Test Description";
        String model = "Test Model";
        when(availableVehicleViewRepository.findByModelAndDescription(model, description))
                .thenReturn(List.of(vehicle));

        List<AvailableVehicleView> result = availableVehicleViewService.filter(description, model, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehicle, result.get(0));
        verify(availableVehicleViewRepository, times(1)).findByModelAndDescription(model, description);
    }

    @Test
    public void testFilterWithModelAndDailyPrice() {
        String model = "Test Model";
        String dailyPrice = "50";
        when(availableVehicleViewRepository.findByModelAndDailyPriceIsLessThanEqual(model, Double.parseDouble(dailyPrice)))
                .thenReturn(List.of(vehicle));

        List<AvailableVehicleView> result = availableVehicleViewService.filter(null, model, dailyPrice);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehicle, result.get(0));
        verify(availableVehicleViewRepository, times(1)).findByModelAndDailyPriceIsLessThanEqual(model, Double.parseDouble(dailyPrice));
    }

    @Test
    public void testFilterWithModelOnly() {
        String model = "Test Model";
        when(availableVehicleViewRepository.findByModel(model))
                .thenReturn(List.of(vehicle));

        List<AvailableVehicleView> result = availableVehicleViewService.filter(null, model, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehicle, result.get(0));
        verify(availableVehicleViewRepository, times(1)).findByModel(model);
    }

    @Test
    public void testFilterWithDailyPriceOnly() {
        String dailyPrice = "50";
        when(availableVehicleViewRepository.findByDailyPriceIsLessThanEqual(Double.parseDouble(dailyPrice)))
                .thenReturn(List.of(vehicle));

        List<AvailableVehicleView> result = availableVehicleViewService.filter(null, null, dailyPrice);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehicle, result.get(0));
        verify(availableVehicleViewRepository, times(1)).findByDailyPriceIsLessThanEqual(Double.parseDouble(dailyPrice));
    }

    @Test
    public void testFilterWithDescriptionOnly() {
        String description = "Test Description";
        when(availableVehicleViewRepository.findByDescription(description))
                .thenReturn(List.of(vehicle));

        List<AvailableVehicleView> result = availableVehicleViewService.filter(description, null, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehicle, result.get(0));
        verify(availableVehicleViewRepository, times(1)).findByDescription(description);
    }

    @Test
    public void testFilterWithNoParameters() {
        when(availableVehicleViewRepository.findAll()).thenReturn(List.of(vehicle));

        List<AvailableVehicleView> result = availableVehicleViewService.filter(null, null, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehicle, result.get(0));
        verify(availableVehicleViewRepository, times(1)).findAll();
    }
}
