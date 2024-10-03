package stud.kea.dk.biografbackend.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;

import java.util.List;

@Service
public class ApiServiceGetMovieImpl implements ApiServiceGetMovie {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public MovieModel createMovie(MovieModel movieModel) {
        return movieRepository.save(movieModel);
    }

    @Override
    public List<MovieModel> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public MovieModel getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public MovieModel updateMovie(MovieModel movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }
}
