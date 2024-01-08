package database.project.carrental.web.controller;

import database.project.carrental.model.AvailableVehicleView;
import database.project.carrental.model.VehicleType;
import database.project.carrental.repository.LocationRepository;
import database.project.carrental.repository.VehicleTypeRepository;
import database.project.carrental.service.AvailableVehicleViewService;
import database.project.carrental.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class VehicleController {
    private final VehicleService vehicleService;
    private final LocationRepository locationRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final AvailableVehicleViewService availableVehicleViewService;


    public VehicleController(VehicleService vehicleService, LocationRepository locationRepository, VehicleTypeRepository vehicleTypeRepository, AvailableVehicleViewService availableVehicleViewService) {
        this.vehicleService = vehicleService;
        this.locationRepository = locationRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.availableVehicleViewService = availableVehicleViewService;
    }
    @GetMapping("/vehicles")
    private String showVehiclePage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<AvailableVehicleView> vehicles=this.availableVehicleViewService.getAvailableVehicles();
        List<VehicleType>vehicleTypes=this.vehicleTypeRepository.findAll();
        model.addAttribute("vehicleTypes",vehicleTypes);
        model.addAttribute("vehicles",vehicles);
        return "index";
    }
    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) String description,
                         @RequestParam(required = false) String vehicleModel,
                         @RequestParam(required = false) String dailyPrice,
                          Model model){
        List<AvailableVehicleView> vehicles=this.availableVehicleViewService.filter(description,vehicleModel,dailyPrice);
        List<VehicleType>vehicleTypes=this.vehicleTypeRepository.findAll();
        model.addAttribute("vehicleTypes",vehicleTypes);
        model.addAttribute("vehicles",vehicles);
        return "index";
    }

    @GetMapping("/vehicles/add")
    private String getAddForm(Model model){
        List<VehicleType> types=this.vehicleTypeRepository.findAll();
        model.addAttribute("types", types);
        return "add-vehicle";

    }
        @PostMapping("/vehicles/add-vehicle")
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
