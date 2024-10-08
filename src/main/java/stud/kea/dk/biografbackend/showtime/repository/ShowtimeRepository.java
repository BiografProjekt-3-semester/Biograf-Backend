package stud.kea.dk.biografbackend.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<ShowtimeModel, Integer> {
    List<ShowtimeModel> findByMovieId(int movieId);

    List<ShowtimeModel> findShowtimeByMovieId(int movieId);

    List<ShowtimeModel> findByTheaterAndMovieDate(TheaterModel theater, LocalDate movieDate);



}
