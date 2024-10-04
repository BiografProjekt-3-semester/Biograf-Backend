package stud.kea.dk.biografbackend.theater.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;
import stud.kea.dk.biografbackend.theater.repository.TheaterRepository;

@Component
public class initDataTheater implements CommandLineRunner {



    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public void run(String... args) throws Exception {
        TheaterModel theater1=new TheaterModel(1,"Lille sal");

        theaterRepository.save(theater1);
    }

}
