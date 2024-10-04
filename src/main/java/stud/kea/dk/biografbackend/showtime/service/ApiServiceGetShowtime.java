package stud.kea.dk.biografbackend.showtime.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;

@Service
public interface ApiServiceGetShowtime {
    public ShowtimeModel createShowtime(ShowtimeModel showtime);
}
