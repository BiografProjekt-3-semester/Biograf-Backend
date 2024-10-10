package stud.kea.dk.biografbackend.movie.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stud.kea.dk.biografbackend.movie.model.MovieModel;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository <MovieModel, Integer> {
    List<MovieModel> findAll(Sort sort);
}
