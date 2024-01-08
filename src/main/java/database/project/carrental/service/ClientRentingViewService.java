package database.project.carrental.service;

import database.project.carrental.model.ClientRentingView;

import java.util.List;

public interface ClientRentingViewService {
    List<ClientRentingView> findByUsername(String username);
    List<ClientRentingView> findAll();
}
