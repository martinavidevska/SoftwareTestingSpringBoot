package database.project.carrental.web.controller;

import database.project.carrental.model.*;
import database.project.carrental.model.exceptions.ClientNotFoundException;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.repository.LocationRepository;
import database.project.carrental.repository.VehicleRepository;
import database.project.carrental.service.RentingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping()
public class RentingController {
    private final RentingService rentingService;
    private final LocationRepository locationRepository;
    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;

    public RentingController(RentingService rentingService, LocationRepository locationRepository, VehicleRepository vehicleRepository, ClientRepository clientRepository) {
        this.rentingService = rentingService;
        this.locationRepository = locationRepository;
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
    }

    @PostMapping("rent")
    private String addRenting(@RequestParam String licensePlate,
                              @RequestParam Location pickedFrom,
                              @RequestParam(required = false) Location returnedTo,
                              @RequestParam LocalDate startRent,
                              @RequestParam(required = false) LocalDate endRent,
                               @RequestParam String username){
        double totalAmount;
        Vehicle vehicle=vehicleRepository.findByLicensePlate(licensePlate);
        if(endRent!=null){
            totalAmount=getTotalAmount(startRent,endRent,vehicle.getDailyPrice());
        }
        else{
            totalAmount=0;
        }
       Client client=this.clientRepository.findByUsername(username).orElseThrow(()->new ClientNotFoundException(username));

        this.rentingService.addRenting(startRent,endRent,totalAmount,vehicle,client,pickedFrom, returnedTo);
        return "redirect:/vehicles";
}
private double getTotalAmount(LocalDate startRent, LocalDate endRent, Double dailyPrice) {
    long numberOfDays = ChronoUnit.DAYS.between(startRent, endRent);
    return numberOfDays * dailyPrice;
    }
}