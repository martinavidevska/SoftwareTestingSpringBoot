package database.project.carrental.service;

import database.project.carrental.model.Client;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface ClientService extends UserDetailsService {

    Client register(String username, String password, String name, String email, String phoneNumber, String driverLicenseNumber, String address);
    Optional<Client> findByUsername(String username);
    Client registerAdmin(String username, String password);

}
