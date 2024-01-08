package database.project.carrental.repository;

import database.project.carrental.model.AvailableVehicleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableVehicleViewRepository extends JpaRepository<AvailableVehicleView, String> {
    List<AvailableVehicleView> findByModel(String model);
    List<AvailableVehicleView> findByDailyPriceIsLessThanEqual(Double dailyPrice);
    List<AvailableVehicleView> findByDescription(String description);
    List<AvailableVehicleView> findByModelAndDescription(String model, String description);
    List<AvailableVehicleView> findByDescriptionAndDailyPriceIsLessThanEqual(String description, Double dailyPrice);
    List<AvailableVehicleView> findByDescriptionAndDailyPriceIsLessThanEqualAndModel(String description, Double dailyPrice, String model);
    List<AvailableVehicleView> findByModelAndDailyPriceIsLessThanEqual(String model, Double dailyPrice);
}

