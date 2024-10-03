package stud.kea.dk.biografbackend.chair.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.biografbackend.chair.model.ChairModel;
import stud.kea.dk.biografbackend.chair.repository.ChairRepository;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;
import stud.kea.dk.biografbackend.theater.repository.TheaterRepository;

import java.util.Optional;

@Component
@Order(2)
public class ChairInitData implements CommandLineRunner {
    @Autowired
    ChairRepository chairRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Override
    public void run(String... args) throws Exception {
        // Associating chairs
        Optional<TheaterModel> theater1 = theaterRepository.findById(1);
        Optional<TheaterModel> theater2 = theaterRepository.findById(2);

        if (theater1.isPresent()) {
            // Inserting chairs into small hall one (20 rows, 12 seats per row)
            for (int row = 1; row <= 20; row++) {
                for (int seat = 1; seat <= 12; seat++) {
                    boolean isSpecialUse = (row == 20 && seat >= 8); // Last 5 chairs in the last row
                    ChairModel chair = new ChairModel(0, seat, row, true, isSpecialUse, theater1.get());
                    chairRepository.save(chair);
                }
            }
        }

        if (theater2.isPresent()) {
            // Inserting chairs into large hall two (25 rows, 16 seats per row)
            for (int row = 1; row <= 25; row++) {
                for (int seat = 1; seat <= 16; seat++) {
                    boolean isSpecialUse = (row == 25 && seat >= 12); // Last 5 chairs in the last row
                    ChairModel chair = new ChairModel(0, seat, row, true, isSpecialUse, theater2.get());
                    chairRepository.save(chair);
                }
            }
        }
    }
}
