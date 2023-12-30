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
    public List<Vehicle> filter(Long vehicleType, Double dailyPrice, String model) {

        VehicleType vehicleTypeId=this.vehicleTypeRepository.findById(vehicleType).orElseThrow();
        if(vehicleTypeId!=null && dailyPrice!=null && model!=null) {
            return this.vehicleRepository.findAllByVehicleTypeAndDailyPriceIsLessThanEqualAndModel(vehicleTypeId,dailyPrice,model);
        }
        else if(vehicleTypeId!=null && dailyPrice!=null){
            return this.vehicleRepository.findAllByVehicleTypeAndDailyPriceIsLessThanEqual(vehicleTypeId,dailyPrice);
        }
        else if (vehicleTypeId != null && model != null) {
            return this.vehicleRepository.findAllByModelAndVehicleType(model,vehicleTypeId);
        }
        else if(model!=null && dailyPrice!=null){
            return this.vehicleRepository.findAllByModelAndDailyPriceIsLessThanEqual(model,dailyPrice);
        }
        else if(model!=null){
            return this.vehicleRepository.findAllByModel(model);
        }
        else if(dailyPrice!=null){
            return this.vehicleRepository.findAllByDailyPriceIsLessThanEqual(dailyPrice);
        }
        else if(vehicleTypeId!=null){
            return this.vehicleRepository.findAllByVehicleTypeId(vehicleTypeId);
        }

        return this.findAll();
    }

}
