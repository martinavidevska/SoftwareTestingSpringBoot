package database.project.carrental.service;

import database.project.carrental.model.*;
import database.project.carrental.model.exceptions.RentingNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface RentingService {
    Renting addRenting(LocalDate startRent, LocalDate endRent, double totalAmount, Vehicle vehicle, Client client, Long pickedFrom, Long returnedTo);
    Renting findById(Long id) throws RentingNotFoundException;
    List<Renting> findAllByUsername(String username);
}
