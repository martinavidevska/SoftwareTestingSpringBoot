package database.project.carrental.service.impl;

import database.project.carrental.model.RentingView;
import database.project.carrental.repository.RentingViewRepository;
import database.project.carrental.service.RentingViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentingViewServiceImpl implements RentingViewService {
    private final RentingViewRepository rentingViewRepository;

    public RentingViewServiceImpl(RentingViewRepository rentingViewRepository) {
        this.rentingViewRepository = rentingViewRepository;
    }

    @Override
    public List<RentingView> findAll() {
        return this.rentingViewRepository.findAll();
    }
}
