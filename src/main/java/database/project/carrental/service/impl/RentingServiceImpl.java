package database.project.carrental.service.impl;

import database.project.carrental.model.*;
import database.project.carrental.model.exceptions.RentingNotFoundException;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.repository.LocationRepository;
import database.project.carrental.repository.RentingRepository;
import database.project.carrental.service.RentingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentingServiceImpl implements RentingService {
    private final RentingRepository rentingRepository;
    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;
    @Autowired
    public RentingServiceImpl(RentingRepository rentingRepository, ClientRepository clientRepository, LocationRepository locationRepository) {
        this.rentingRepository = rentingRepository;
        this.clientRepository = clientRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Renting addRenting(LocalDate startRent, LocalDate endRent, double totalAmount, Vehicle vehicle, Client client,  Long pickedFromId, Long returnedToId) {
        Location pickedFrom = locationRepository.findById(pickedFromId).orElseThrow();
        Location returnedTo = locationRepository.findById(returnedToId).orElseThrow();
        return this.rentingRepository.save(new Renting(startRent,endRent,totalAmount,vehicle, client,pickedFrom,returnedTo));
    }

    @Override
    public Renting findById(Long id){
        return this.rentingRepository.findById(id).orElseThrow(() -> new RentingNotFoundException(id));
    }

    @Override
    public List<Renting> findAllByUsername(String username) {
        Optional<Client> client = this.clientRepository.findByUsername(username);
        return this.rentingRepository.findAllByClient(client);
    }

}
