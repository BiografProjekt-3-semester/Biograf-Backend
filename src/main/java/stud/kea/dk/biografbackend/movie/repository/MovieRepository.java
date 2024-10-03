package stud.kea.dk.biografbackend.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.biografbackend.movie.model.MovieModel;

public interface MovieRepository extends JpaRepository <MovieModel, Integer> {
}
