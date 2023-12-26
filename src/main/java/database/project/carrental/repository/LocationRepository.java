package database.project.carrental.repository;

import database.project.carrental.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LocationRepository extends JpaRepository<Location, Long > {
    Optional<Location> findById(Long id);

}
