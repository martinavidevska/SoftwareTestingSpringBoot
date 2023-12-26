package database.project.carrental.repository;

import database.project.carrental.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Vehicle findByLicensePlate(String licensePlate);
    List<Vehicle> findAllByVehicleTypeId(Long vehicleTypeId);
    Vehicle findByModel(String model);
    Vehicle findBySeats(Integer seats);

}
