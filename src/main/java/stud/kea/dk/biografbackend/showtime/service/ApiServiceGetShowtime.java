package stud.kea.dk.biografbackend.showtime.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ApiServiceGetShowtime {
    public ShowtimeModel createShowtime(ShowtimeModel showtime);

}
