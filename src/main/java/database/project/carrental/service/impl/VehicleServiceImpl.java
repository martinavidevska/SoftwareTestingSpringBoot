package database.project.carrental.service.impl;

import database.project.carrental.model.Vehicle;
import database.project.carrental.model.VehicleType;
import database.project.carrental.repository.VehicleRepository;
import database.project.carrental.repository.VehicleTypeRepository;
import database.project.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Override
    public Vehicle findByLicensePlate(String licensePlate) {
        return this.vehicleRepository.findByLicensePlate(licensePlate);
    }

    @Override
    public Vehicle remove(String licensePlate) {
        Vehicle vehicle=this.findByLicensePlate(licensePlate);
         this.vehicleRepository.delete(vehicle);
         return vehicle;
    }

    @Override
    public Vehicle add( String licensePlate, String model, String brand, Integer seats, Double dailyPrice, Integer bags, VehicleType vehicleType,String pictureLink) {
        return  this.vehicleRepository.save(new Vehicle(licensePlate,model,brand,seats,dailyPrice, bags,vehicleType,pictureLink));
    }

    @Override
    public List<Vehicle> findAll() {
        return this.vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> filter(String description, String dailyPrice, String model) {
        if ((description != null && !description.isEmpty()) && (dailyPrice != null && !dailyPrice.isEmpty()) && (model != null && !model.isEmpty())) {
            VehicleType vehicleType = this.vehicleTypeRepository.findByDescription(description).orElseThrow();
            return this.vehicleRepository.findAllByVehicleTypeAndDailyPriceIsLessThanEqualAndModel(vehicleType, Double.parseDouble(dailyPrice), model);
        } else if ((description != null && !description.isEmpty()) && (dailyPrice != null && !dailyPrice.isEmpty())) {
            VehicleType vehicleType = this.vehicleTypeRepository.findByDescription(description).orElseThrow();
            return this.vehicleRepository.findAllByVehicleTypeAndDailyPriceIsLessThanEqual(vehicleType, Double.parseDouble(dailyPrice));
        } else if ((description != null && !description.isEmpty()) && (model != null && !model.isEmpty())) {
            VehicleType vehicleType = this.vehicleTypeRepository.findByDescription(description).orElseThrow();
            return this.vehicleRepository.findAllByModelAndVehicleType(model, vehicleType);
        } else if ((model != null && !model.isEmpty()) && (dailyPrice != null && !dailyPrice.isEmpty())) {
            return this.vehicleRepository.findAllByModelAndDailyPriceIsLessThanEqual(model, Double.parseDouble(dailyPrice));
        } else if (model != null && !model.isEmpty()) {
            return this.vehicleRepository.findAllByModel(model);
        } else if (dailyPrice != null && !dailyPrice.isEmpty()) {
            return this.vehicleRepository.findAllByDailyPriceIsLessThanEqual(Double.parseDouble(dailyPrice));
        } else if (description != null && !description.isEmpty()) {
            VehicleType vehicleType = this.vehicleTypeRepository.findByDescription(description).orElseThrow();
            return this.vehicleRepository.findAllByVehicleType(vehicleType);
        } else {
            return this.findAll();
        }
    }



}
