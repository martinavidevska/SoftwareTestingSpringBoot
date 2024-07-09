package database.project.carrental.model;

import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class VehicleTest {

    @Test
    public void testConstructor() {

        VehicleType vehicleType = new VehicleType("BMW");
        Vehicle vehicle = new Vehicle("SK0833JK", "Polo", "Volkswagen", 2, 2300.0, 2, vehicleType, "https://images.hindustantimes.com/auto/img/2022/04/08/1600x900/Volkswagen_Polo_Legend_edition_1649044005572_1649382819575.jpg");

        assertThat(vehicle.getLicensePlate()).isEqualTo("SK0833JK");
        assertThat(vehicle.getModel()).isEqualTo("Polo");
        assertThat(vehicle.getBrand()).isEqualTo("Volkswagen");
        assertThat(vehicle.getSeats()).isEqualTo(2);
        assertThat(vehicle.getDailyPrice()).isEqualTo(2300.0);
        assertThat(vehicle.getBags()).isEqualTo(2);
        assertThat(vehicle.getVehicleType()).isEqualTo(vehicleType);
        assertThat(vehicle.getPictureLink()).isEqualTo("https://images.hindustantimes.com/auto/img/2022/04/08/1600x900/Volkswagen_Polo_Legend_edition_1649044005572_1649382819575.jpg");
    }

}
