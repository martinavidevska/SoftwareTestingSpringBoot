package database.project.carrental.repository;

import database.project.carrental.model.Client;
import database.project.carrental.model.Renting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentingRepository extends JpaRepository<Renting, Long> {
  List<Renting> findAllByClient(Optional<Client> client);

}
