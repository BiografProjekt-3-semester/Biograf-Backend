package stud.kea.dk.biografbackend.movie.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.showtime.repository.ShowtimeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements MovieCRUD {


    final private MovieRepository movieRepository;
    final private ShowtimeRepository showtimeRepository;

    public MovieService(MovieRepository movieRepository, ShowtimeRepository showtimeRepository) {
        this.movieRepository = movieRepository;
        this.showtimeRepository=showtimeRepository;
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
        if (movieModel == null) {
            throw new IllegalArgumentException("Movie data cannot be null");
        }
        if (movieModel.getTitle() == null || movieModel.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Movie title cannot be empty");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid movie ID");
        }
        if (movieModel.getTitle().length() > 255) {
            throw new IllegalArgumentException("Movie title cannot exceed 255 characters");
        }

        Optional<MovieModel> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            MovieModel movieToUpdate = movie.get();
            movieToUpdate.setTitle(movieModel.getTitle());
            movieToUpdate.setDescription(movieModel.getDescription());
            movieToUpdate.setDurationEkstra(movieModel.getDurationEkstra());

            return movieRepository.save(movieToUpdate);
        } else {
            throw new EntityNotFoundException("Movie not found with id " + id);
        }
    }
    public String checkForShowtimesBeforeDelete(int movieid) {
        List<ShowtimeModel> showtimes = showtimeRepository.findByMovieId(movieid);
        if(!showtimes.isEmpty()) {
            return "Filmen har stadig visningstider. Ønsker du stadig at slette den?";
        }
        return "Filmen blev slettet";

    }
    public String deleteMovieConfirmed(int movieid) {
        movieRepository.deleteById(movieid);
        return "Filmen er slettet";
    }

}
