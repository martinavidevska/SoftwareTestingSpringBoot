package database.project.carrental.service;

import database.project.carrental.model.Vehicle;
import database.project.carrental.model.VehicleType;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
   Vehicle findByLicensePlate(String licensePlate);

    Vehicle remove(String licensePlate );
    Vehicle add(String licensePlate, String model, String brand, Integer seats, Double dailyPrice, Integer bags, VehicleType vehicleType, String pictureLink);
    List<Vehicle>findAll();
    List<Vehicle> filter(Long vehicleTypeId, Double dailyPrice, String model);
}
