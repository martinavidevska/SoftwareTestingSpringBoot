package database.project.carrental.service.impl;

import database.project.carrental.model.ClientRentingView;
import database.project.carrental.repository.ClientRentingViewRepository;
import database.project.carrental.service.ClientRentingViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientRentingViewServiceImpl implements ClientRentingViewService {
    private final ClientRentingViewRepository clientRentingViewRepository;

    public ClientRentingViewServiceImpl(ClientRentingViewRepository clientRentingViewRepository) {
        this.clientRentingViewRepository = clientRentingViewRepository;
    }

    @Override
    public List<ClientRentingView> findByUsername(String username) {
        return this.clientRentingViewRepository.findAllByUsername(username);
    }

    @Override
    public List<ClientRentingView> findAll() {
        return this.clientRentingViewRepository.findAll();
    }
}
