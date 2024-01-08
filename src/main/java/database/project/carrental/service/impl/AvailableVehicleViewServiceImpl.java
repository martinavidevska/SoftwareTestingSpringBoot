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
        if ((!description.isEmpty() || description!=null) && dailyPrice != null && !model.isEmpty()) {
            // Handle the case when dailyPrice is not empty and not null
            return this.availableVehicleViewRepository.findByDescriptionAndDailyPriceIsLessThanEqualAndModel(description, Double.parseDouble(dailyPrice), model);
        } else if (!description.isEmpty() && dailyPrice != null && !dailyPrice.isEmpty()) {
            // Handle the case when dailyPrice is not empty but can be null
            return this.availableVehicleViewRepository.findByDescriptionAndDailyPriceIsLessThanEqual(description, Double.parseDouble(dailyPrice));
        } else if (!description.isEmpty() && !model.isEmpty()) {
            return this.availableVehicleViewRepository.findByModelAndDescription(model, description);
        } else if (!model.isEmpty() && dailyPrice != null && !dailyPrice.isEmpty()) {
            return this.availableVehicleViewRepository.findByModelAndDailyPriceIsLessThanEqual(model, Double.parseDouble(dailyPrice));
        } else if (!model.isEmpty()) {
            return this.availableVehicleViewRepository.findByModel(model);
        } else if (dailyPrice != null && !dailyPrice.isEmpty()) {
            return this.availableVehicleViewRepository.findByDailyPriceIsLessThanEqual(Double.parseDouble(dailyPrice));
        } else if (!description.isEmpty()) {
            return this.availableVehicleViewRepository.findByDescription(description);
        } else {
            return this.availableVehicleViewRepository.findAll();
        }
    }

}

