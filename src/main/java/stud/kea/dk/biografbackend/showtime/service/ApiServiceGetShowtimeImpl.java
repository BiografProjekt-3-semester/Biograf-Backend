package stud.kea.dk.biografbackend.showtime.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.showtime.repository.ShowtimeRepository;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;
import stud.kea.dk.biografbackend.theater.repository.TheaterRepository;

import java.time.LocalTime;
import java.util.List;


@Service
public class ApiServiceGetShowtimeImpl implements ApiServiceGetShowtime {


    final private ShowtimeRepository showtimeRepository;
    final private MovieRepository movieRepository;
    final private TheaterRepository theaterRepository;

    public ApiServiceGetShowtimeImpl(ShowtimeRepository showtimeRepository, MovieRepository movieRepository, TheaterRepository theaterRepository) {
        this.showtimeRepository = showtimeRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
    }

    @Override
    public ShowtimeModel createShowtime(ShowtimeModel showtime) {
        // Hent film og teater fra databasen baseret på de sendte ID'er
        MovieModel movie = movieRepository.findById(showtime.getMovie().getId()).orElseThrow(
                () -> new RuntimeException("Movie not found"));
        TheaterModel theater = theaterRepository.findById(showtime.getTheater().getId()).orElseThrow(
                () -> new RuntimeException("Theater not found"));

        // Sæt de hentede film og teater ind i showtime
        showtime.setMovie(movie);
        showtime.setTheater(theater);

        // Gem showtime
        return showtimeRepository.save(showtime);
    }

    public List<ShowtimeModel> getShowTimesByMovieId(int movieId) {
        return showtimeRepository.findShowtimeByMovieId(movieId);
    }

    public boolean isShowtimeValid(ShowtimeModel newShowtime) {
        List<ShowtimeModel> existingShowtimes = showtimeRepository.findByTheaterAndMovieDate(newShowtime.getTheater(), newShowtime.getMovieDate());

        for(ShowtimeModel eShowtimes : existingShowtimes) {
            LocalTime cleaning = eShowtimes.getEndTime().plusMinutes(30);

            if (newShowtime.getStartTime().isBefore(cleaning) && newShowtime.getEndTime().isAfter(eShowtimes.getStartTime())){
                return false;
            }
        }
        return true;

    }

}
