package database.project.carrental.repository;

import database.project.carrental.model.Vehicle;
import database.project.carrental.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Vehicle findByLicensePlate(String licensePlate);
    List<Vehicle> findAllByVehicleType(VehicleType vehicleType);
    Vehicle findByModel(String model);
    Vehicle findBySeats(Integer seats);
    List<Vehicle> findAllByModel(String model);
    List<Vehicle> findAllByDailyPriceIsLessThanEqual(Double dailyPrice);
    List<Vehicle> findAllByModelAndVehicleType(String model, VehicleType vehicleType);
    List<Vehicle> findAllByVehicleTypeAndDailyPriceIsLessThanEqual(VehicleType vehicleType, Double dailyPrice);
    List<Vehicle> findAllByVehicleTypeAndDailyPriceIsLessThanEqualAndModel(VehicleType vehicleType, Double dailyPrice, String model);
    List<Vehicle> findAllByModelAndDailyPriceIsLessThanEqual(String model, Double dailyPrice);
}
