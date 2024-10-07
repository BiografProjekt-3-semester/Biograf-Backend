package stud.kea.dk.biografbackend.theater.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.chair.model.ChairModel;
import stud.kea.dk.biografbackend.chair.repository.ChairRepository;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;
import stud.kea.dk.biografbackend.theater.repository.TheaterRepository;

import java.util.List;

@Service
public class TheaterService {

    final private TheaterRepository theaterRepository;
    final private ChairRepository chairRepository;

    public TheaterService(TheaterRepository theaterRepository, ChairRepository chairRepository) {
        this.theaterRepository = theaterRepository;
        this.chairRepository = chairRepository;
    }

    public void addChairsToTheater(int theaterId, List<ChairModel> chairs) {
        // Retrieve the theater by id
        TheaterModel theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found"));

        // Add each chair to the theater and save it
        for (ChairModel chair : chairs) {
            chair.setTheater(theater);
            chairRepository.save(chair);
        }
    }
}

