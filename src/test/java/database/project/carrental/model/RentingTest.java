package database.project.carrental.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentingTest {

    @Mock
    private Vehicle vehicle;

    @Mock
    private Client client;

    @Mock
    private Location pickedFrom;

    @Mock
    private Location returnedTo;

    @Test
    public void testRentingCreation() {
        LocalDate startRent = LocalDate.of(2024, 7, 10);
        LocalDate endRent = LocalDate.of(2024, 7, 15);
        double totalAmount = 500.0;

        Renting renting = new Renting(startRent, endRent, totalAmount, vehicle, client, pickedFrom, returnedTo);

        assertEquals(startRent, renting.getStartRent());
        assertEquals(endRent, renting.getEndRent());
        assertEquals(totalAmount, renting.getTotalAmount());
        assertEquals(vehicle, renting.getVehicle());
        assertEquals(client, renting.getClient());
        assertEquals(pickedFrom, renting.getPickedFrom());
        assertEquals(returnedTo, renting.getReturnedTo());
    }
}
