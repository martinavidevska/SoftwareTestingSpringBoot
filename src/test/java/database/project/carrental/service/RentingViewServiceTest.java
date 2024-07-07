package database.project.carrental.service;

import database.project.carrental.model.RentingView;
import database.project.carrental.repository.RentingViewRepository;
import database.project.carrental.service.RentingViewService;
import database.project.carrental.service.impl.RentingViewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentingViewServiceTest {

    @Mock
    private RentingViewRepository rentingViewRepository;

    @InjectMocks
    private RentingViewServiceImpl rentingViewService;

    private RentingView rentingView;

    @BeforeEach
    public void setUp() {
        rentingView = new RentingView();
    }

    @Test
    public void testFindAll() {
        when(rentingViewRepository.findAll()).thenReturn(List.of(rentingView));

        List<RentingView> result = rentingViewService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(rentingViewRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllReturnsEmptyList() {
        when(rentingViewRepository.findAll()).thenReturn(List.of());

        List<RentingView> result = rentingViewService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(rentingViewRepository, times(1)).findAll();
    }
}
