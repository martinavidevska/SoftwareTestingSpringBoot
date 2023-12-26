package database.project.carrental.service.impl;


import database.project.carrental.model.Client;
import database.project.carrental.model.exceptions.InvalidArgumentsException;
import database.project.carrental.model.exceptions.InvalidUserCredentialsException;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final ClientRepository clientRepository;

    public AuthServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client login(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        return clientRepository.findByUsernameAndPassword(username, password).orElseThrow(()->new InvalidArgumentsException());

    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

}