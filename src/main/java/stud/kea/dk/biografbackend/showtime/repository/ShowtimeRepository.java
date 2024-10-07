package stud.kea.dk.biografbackend.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;

import java.util.List;

public interface ShowtimeRepository extends JpaRepository<ShowtimeModel, Integer> {

    List<ShowtimeModel> findShowtimeByMovieId(int movieId);


}
