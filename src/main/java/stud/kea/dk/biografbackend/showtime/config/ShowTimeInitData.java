package stud.kea.dk.biografbackend.showtime.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.showtime.repository.ShowtimeRepository;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;
import stud.kea.dk.biografbackend.theater.repository.TheaterRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Component
@Order(4)
public class ShowTimeInitData implements CommandLineRunner {

    final private ShowtimeRepository showtimeRepository;
    final private MovieRepository movieRepository;
    final private TheaterRepository theaterRepository;

    public ShowTimeInitData(ShowtimeRepository showtimeRepository, MovieRepository movieRepository, TheaterRepository theaterRepository) {
        this.showtimeRepository = showtimeRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
    }

    @Override
    public void run(String... args) {
        if (showtimeRepository.findAll().isEmpty()) {
            List<MovieModel> movies = movieRepository.findAll();
            List<TheaterModel> theaters = theaterRepository.findAll();

            if (!movies.isEmpty() && !theaters.isEmpty()) {
                // Varighed for hver af de 10 film
                //koden skal rettes til hvis der er flere film
                int[] movieDurations = {100, 87, 88, 92, 91, 83, 104, 84, 83, 89};
                Random random = new Random();
                LocalDate today = LocalDate.now();

                // Laver x showtimes for hver film (skal rette i j < x)
                for (int i = 0; i < 10; i++) {
                    MovieModel movie = movies.get(i);
                    int duration = movieDurations[i];

                    for (int j = 0; j < 3; j++) {
                        // Vælger tilfældig sal
                        TheaterModel randomTheater = theaters.get(random.nextInt(theaters.size()));
                        // Vælger tilfældig tid mellem 10:00 og 20:59
                        LocalTime randomStartTime = LocalTime.of(random.nextInt(10) + 10, random.nextInt(60));
                        LocalTime endTime = randomStartTime.plusMinutes(duration);

                        ShowtimeModel showtime = new ShowtimeModel();
                        // Vælger tilfældig dato inden for 30 dage
                        showtime.setMovieDate(today.plusDays(random.nextInt(30)));
                        showtime.setStartTime(randomStartTime);
                        showtime.setEndTime(endTime);
                        // Vælger tilfældig pris mellem 75 og 100
                        showtime.setPrice(75.0 + random.nextDouble() * 25.0);
                        showtime.setMovie(movie);
                        showtime.setTheater(randomTheater);
                        // Gemmer hver showtime
                        showtimeRepository.save(showtime);
                    }
                }
            }
        }
    }
}
