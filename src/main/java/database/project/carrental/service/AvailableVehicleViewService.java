package database.project.carrental.service;


import database.project.carrental.model.AvailableVehicleView;

import java.util.List;

public interface AvailableVehicleViewService {
    List<AvailableVehicleView> getAvailableVehicles();
    List<AvailableVehicleView>filter (String description, String model, String dailyPrice);
}
