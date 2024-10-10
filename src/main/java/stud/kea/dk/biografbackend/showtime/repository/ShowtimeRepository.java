package stud.kea.dk.biografbackend.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<ShowtimeModel, Integer> {
    List<ShowtimeModel> findByMovieId(int movieId);

    List<ShowtimeModel> findShowtimeByMovieIdOrderByMovieDateAscStartTimeAsc(int movieId);

    List<ShowtimeModel> findByTheaterAndMovieDate(TheaterModel theater, LocalDate movieDate);

    List<ShowtimeModel> findByMovieDateBefore(LocalDate date);

    List<ShowtimeModel> findByMovieDateAndStartTimeBefore(LocalDate date, LocalTime time);

    List<ShowtimeModel> findByMovieIdAndMovieDate(int movieId, LocalDate movieDate);


}
