package stud.kea.dk.biografbackend.theater.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.biografbackend.theater.repository.TheaterRepository;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;

@Component
@Order(1)
public class TheaterInitData implements CommandLineRunner {

    final private TheaterRepository theaterRepository;

    public TheaterInitData(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @Override
    public void run(String... args) {
        if (theaterRepository.findAll().isEmpty()) {
            TheaterModel theater1 = new TheaterModel();
            theater1.setName("Small Theater");

            TheaterModel theater2 = new TheaterModel();
            theater2.setName("Large Theater");

            theaterRepository.save(theater1);
            theaterRepository.save(theater2);
        }
    }
}
