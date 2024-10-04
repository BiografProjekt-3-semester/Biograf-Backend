package stud.kea.dk.biografbackend.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;

public interface TheaterRepository extends JpaRepository<TheaterModel, Integer> {
}
