package database.project.carrental.web.controller;

import database.project.carrental.model.Location;
import database.project.carrental.model.Vehicle;
import database.project.carrental.model.exceptions.VehicleNotFoundException;
import database.project.carrental.repository.LocationRepository;
import database.project.carrental.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final LocationRepository locationRepository;

    public VehicleController(VehicleService vehicleService, LocationRepository locationRepository) {
        this.vehicleService = vehicleService;
        this.locationRepository = locationRepository;
    }
    @GetMapping()
    private String showVehiclePage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Vehicle> vehicles=this.vehicleService.findAll();
        model.addAttribute("vehicles",vehicles);
        return "index";
    }

    @GetMapping("/rent/{id}")
    private String showVehicleById(@PathVariable String id, Model model){
            Vehicle vehicle = vehicleService.findByLicensePlate(id);
            String typeDescription = vehicle.getVehicleType().getDescription();
            List<Location>allLocations=locationRepository.findAll();

            model.addAttribute("vehicle", vehicle);
            model.addAttribute("vehicleType", typeDescription);
            model.addAttribute("locations",allLocations);
            //Treba da promenam
            return "rent";

    }

}
