package stud.kea.dk.biografbackend.showtime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.showtime.repository.ShowtimeRepository;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;
import stud.kea.dk.biografbackend.theater.repository.TheaterRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceGetShowtimeImpl implements ApiServiceGetShowtime {

    @Autowired
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

        return showtimeRepository.findShowtimeByMovieIdOrderByMovieDateAscStartTimeAsc(movieId);
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

    @Override
    public List<ShowtimeModel> deleteExpiredShowtimes() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        List<ShowtimeModel> expiredShowtimes = showtimeRepository.findByMovieDateBefore(today);

        List<ShowtimeModel> todayExpiredShowtimes =showtimeRepository.findByMovieDateAndStartTimeBefore(today,now);

        List<ShowtimeModel> allExpiredShowtimes= new ArrayList<>(expiredShowtimes);
        allExpiredShowtimes.addAll(todayExpiredShowtimes);

        if(!expiredShowtimes.isEmpty()){
            showtimeRepository.deleteAll(allExpiredShowtimes);
        }
        return allExpiredShowtimes;
    }

    public List<ShowtimeModel> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    public ShowtimeModel getShowtimeById(int showtimeId) {
        return showtimeRepository.findById(showtimeId).orElse(null);
    }
    public List<ShowtimeModel> getShowTimesByMovieIdAndDate(int movieId, LocalDate date) {
        return showtimeRepository.findByMovieIdAndMovieDate(movieId, date);
    }

    public ShowtimeModel getShowtimeById(Integer id) {
        return showtimeRepository.findById(id).orElse(null);
    }

    public ShowtimeModel saveShowtime(ShowtimeModel showtime) {
        return showtimeRepository.save(showtime);
    }
}
