package database.project.carrental.service.impl;

import database.project.carrental.model.AvailableVehicleView;
import database.project.carrental.repository.AvailableVehicleViewRepository;
import database.project.carrental.service.AvailableVehicleViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableVehicleViewServiceImpl implements AvailableVehicleViewService {

    private final AvailableVehicleViewRepository availableVehicleViewRepository;

    public AvailableVehicleViewServiceImpl(AvailableVehicleViewRepository availableVehicleViewRepository) {
        this.availableVehicleViewRepository = availableVehicleViewRepository;
    }

    @Override
    public List<AvailableVehicleView> getAvailableVehicles() {
        return this.availableVehicleViewRepository.findAll();
    }

    @Override
    public List<AvailableVehicleView> filter(String description, String model, String dailyPrice) {
        if ((description != null && !description.isEmpty()) && (dailyPrice != null && !dailyPrice.isEmpty()) && (model != null && !model.isEmpty())) {
            // Handle the case when dailyPrice, description, and model are not empty or null
            return this.availableVehicleViewRepository.findByDescriptionAndDailyPriceIsLessThanEqualAndModel(description, Double.parseDouble(dailyPrice), model);
        } else if ((description != null && !description.isEmpty()) && (dailyPrice != null && !dailyPrice.isEmpty())) {
            // Handle the case when dailyPrice and description are not empty or null
            return this.availableVehicleViewRepository.findByDescriptionAndDailyPriceIsLessThanEqual(description, Double.parseDouble(dailyPrice));
        } else if ((description != null && !description.isEmpty()) && (model != null && !model.isEmpty())) {
            // Handle the case when description and model are not empty or null
            return this.availableVehicleViewRepository.findByModelAndDescription(model, description);
        } else if ((model != null && !model.isEmpty()) && (dailyPrice != null && !dailyPrice.isEmpty())) {
            // Handle the case when dailyPrice and model are not empty or null
            return this.availableVehicleViewRepository.findByModelAndDailyPriceIsLessThanEqual(model, Double.parseDouble(dailyPrice));
        } else if (model != null && !model.isEmpty()) {
            // Handle the case when model is not empty or null
            return this.availableVehicleViewRepository.findByModel(model);
        } else if (dailyPrice != null && !dailyPrice.isEmpty()) {
            // Handle the case when dailyPrice is not empty or null
            return this.availableVehicleViewRepository.findByDailyPriceIsLessThanEqual(Double.parseDouble(dailyPrice));
        } else if (description != null && !description.isEmpty()) {
            // Handle the case when description is not empty or null
            return this.availableVehicleViewRepository.findByDescription(description);
        } else {
            return this.availableVehicleViewRepository.findAll();
        }
    }


}

