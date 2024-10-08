package stud.kea.dk.biografbackend.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;

import java.util.List;

public interface TheaterRepository extends JpaRepository<TheaterModel, Integer> {

}
