package stud.kea.dk.biografbackend.movie.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements MovieCRUD {


    final private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieModel createMovie(MovieModel movie) {
        return movieRepository.save(movie);
    }

    public List<MovieModel> getAllMovies() {
        return movieRepository.findAll();
    }

    public MovieModel getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public MovieModel updateMovie(int id, MovieModel movieModel) {
        Optional<MovieModel> movie = movieRepository.findById(id);

        if (movie.isPresent()) {
            MovieModel movieToUpdate = movie.get();
            movieToUpdate.setTitle(movieModel.getTitle());
            movieToUpdate.setDescription(movieModel.getDescription());
            movieToUpdate.setDurationEkstra(movieModel.getDurationEkstra());
            movieToUpdate.setPicture(movieModel.getPicture());

            return movieRepository.save(movieToUpdate);
        } else {
            throw new EntityNotFoundException("Movie not found with id " + id);
        }
    }
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

}
