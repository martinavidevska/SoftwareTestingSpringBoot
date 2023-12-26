package database.project.carrental.service.impl;

import database.project.carrental.model.Client;
import database.project.carrental.model.Role;
import database.project.carrental.model.exceptions.ClientNotFoundException;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.service.ClientService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client register(String name, String password , String email, String phoneNumber, String driverLicenseNumber, String address) {
        return clientRepository.save(new Client(name, passwordEncoder.encode(password),email,phoneNumber,driverLicenseNumber,address, Role.USER));
    }
    @Override
    public Optional<Client> findByUsername(String username) {
        return this.clientRepository.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
}
