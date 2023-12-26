package database.project.carrental.service.impl;

import database.project.carrental.model.Location;
import database.project.carrental.repository.LocationRepository;
import database.project.carrental.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> listAll() {
        return this.locationRepository.findAll();
    }
}
