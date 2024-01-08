package database.project.carrental.repository;

import database.project.carrental.model.ClientRentingView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRentingViewRepository extends JpaRepository<ClientRentingView, String> {
    List<ClientRentingView> findAllByUsername(String username);
}
