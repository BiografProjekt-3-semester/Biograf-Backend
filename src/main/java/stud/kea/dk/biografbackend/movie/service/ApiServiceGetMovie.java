package stud.kea.dk.biografbackend.movie.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.movie.model.MovieModel;

import java.util.List;

@Service
public interface ApiServiceGetMovie {

    MovieModel createMovie (MovieModel movieModel);
    List<MovieModel> getAllMovies();
    MovieModel getMovieById (int id);
    MovieModel updateMovie (MovieModel movie);
    void deleteMovie (int id);

}
