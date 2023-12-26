package database.project.carrental.service.impl;

import database.project.carrental.model.Vehicle;
import database.project.carrental.model.VehicleType;
import database.project.carrental.model.exceptions.VehicleNotFoundException;
import database.project.carrental.repository.VehicleRepository;
import database.project.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle findByLicensePlate(String licensePlate) {
        return this.vehicleRepository.findByLicensePlate(licensePlate);
    }

    @Override
    public List<Vehicle> findByVehicleType(Long vehicleTypeId) {
        return  this.vehicleRepository.findAllByVehicleTypeId(vehicleTypeId);
    }

    @Override
    public Vehicle findByModel(String model) {
        return  this.vehicleRepository.findByModel(model);
    }

    @Override
    public Vehicle findBySeats(Integer seats) {
        return  this.vehicleRepository.findBySeats(seats);
    }

    @Override
    public Vehicle remove(String licensePlate) {
        Vehicle vehicle=this.findByLicensePlate(licensePlate);
         this.vehicleRepository.delete(vehicle);
         return vehicle;
    }

    @Override
    public Vehicle add( String licensePlate, String model, String brand, Integer seats, Double dailyPrice, Integer bags, VehicleType vehicleType) {
        return  this.vehicleRepository.save(new Vehicle(licensePlate,model,brand,seats,dailyPrice, bags,vehicleType));
    }

    @Override
    public List<Vehicle> findAll() {
        return this.vehicleRepository.findAll();
    }
}
