package stud.kea.dk.biografbackend.showtime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.showtime.repository.ShowtimeRepository;
@Service
public class ShowtimeServiceImpl implements ShowtimeService{

    @Autowired
    ShowtimeRepository showtimeRepository;

    @Override
    public ShowtimeModel createShowtime(ShowtimeModel showtime) {
        return showtimeRepository.save(showtime);
    }
}
