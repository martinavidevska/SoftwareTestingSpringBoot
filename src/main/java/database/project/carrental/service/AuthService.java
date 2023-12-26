package database.project.carrental.service;


import database.project.carrental.model.Client;

import java.util.List;

public interface AuthService {
    Client login(String username, String password);

    List<Client> findAll();

}