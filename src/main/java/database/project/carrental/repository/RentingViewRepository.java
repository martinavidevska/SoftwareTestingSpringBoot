package database.project.carrental.repository;

import database.project.carrental.model.RentingView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentingViewRepository extends JpaRepository<RentingView,Long> {
}
