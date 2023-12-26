package database.project.carrental.repository;

import database.project.carrental.model.Client;
import database.project.carrental.model.Renting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);

    Client findByEmail(String email);
    Optional<Client> findByUsernameAndPassword(String username, String password);

}