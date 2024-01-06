package database.project.carrental.web.controller;

import database.project.carrental.model.Location;
import database.project.carrental.model.Vehicle;
import database.project.carrental.model.VehicleType;
import database.project.carrental.repository.LocationRepository;
import database.project.carrental.repository.VehicleTypeRepository;
import database.project.carrental.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final LocationRepository locationRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    public VehicleController(VehicleService vehicleService, LocationRepository locationRepository, VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleService = vehicleService;
        this.locationRepository = locationRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }
    @GetMapping()
    private String showVehiclePage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Vehicle> vehicles=this.vehicleService.findAll();
        List<VehicleType>vehicleTypes=this.vehicleTypeRepository.findAll();
        model.addAttribute("vehicleTypes",vehicleTypes);
        model.addAttribute("vehicles",vehicles);
        return "index";
    }


    @GetMapping("/filter")
    public String filter(@RequestParam(required = false)Long vehicleTypeId,
                          @RequestParam(required = false)String vehicleModel,
                          @RequestParam(required = false)String dailyPrice,
                          Model model){

        try {
            if (dailyPrice != null) {
                Double dailyPrice1 = Double.parseDouble(dailyPrice);
            List<Vehicle> filterVehicles = vehicleService.filter(vehicleTypeId, dailyPrice1, vehicleModel);
            model.addAttribute("vehicles", filterVehicles);
            }
            else {
                List<Vehicle> filterVehicles = vehicleService.filter(vehicleTypeId, null, vehicleModel);
                model.addAttribute("vehicles", filterVehicles);
            }
        } catch (NumberFormatException e) {
            // Handle parsing error (invalid double)
            model.addAttribute("hasError", true);
            model.addAttribute("error", "Invalid dailyPrice format");
        }
        return "index";
    }

    @GetMapping("/add")
    private String getAddForm(Model model){
        List<VehicleType> types=this.vehicleTypeRepository.findAll();
        model.addAttribute("types", types);
        return "add-vehicle";

    }
        @PostMapping("/add-vehicle")
    private String addVehicle(@RequestParam String licensePlate,
                              @RequestParam String model,
                              @RequestParam String brand,
                              @RequestParam Integer seats,
                              @RequestParam Double dailyPrice,
                              @RequestParam Integer bags,
                              @RequestParam VehicleType vehicleType,
                              @RequestParam String pictureLink){

        this.vehicleService.add(licensePlate, model, brand, seats, dailyPrice, bags, vehicleType, pictureLink);
        return "redirect:/vehicles";
        }

}
