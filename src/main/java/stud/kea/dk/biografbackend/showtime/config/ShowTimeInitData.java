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
                // Varigheden på hver film
                int[] movieDurations = {100, 87, 88, 92, 91, 83, 104, 84, 83, 89};
                Random random = new Random();
                LocalDate today = LocalDate.now();

                // Laver to showtimes for hver film
                for (int i = 0; i < 10; i++) {
                    MovieModel movie = movies.get(i);
                    int duration = movieDurations[i];

                    // Laver den første showtime
                    TheaterModel randomTheater1 = theaters.get(random.nextInt(theaters.size()));
                    // Laver tilfældig tid mellem 10:00 og 20:59
                    LocalTime randomStartTime1 = LocalTime.of(random.nextInt(10) + 10, random.nextInt(60));
                    LocalTime endTime1 = randomStartTime1.plusMinutes(duration);

                    ShowtimeModel showtime1 = new ShowtimeModel();
                    // Laver tilfældig dato indien for 30 dage
                    showtime1.setMovieDate(today.plusDays(random.nextInt(30)));
                    showtime1.setStartTime(randomStartTime1);
                    showtime1.setEndTime(endTime1);
                    // Laver tilfældig pris mellem 75 og 100
                    showtime1.setPrice(75.0 + random.nextDouble() * 25.0);
                    showtime1.setMovie(movie);
                    showtime1.setTheater(randomTheater1);

                    // Gemmer den først showtime
                    showtimeRepository.save(showtime1);

                    // Sikre den anden showtime er mindst 30 min efter slutningen af den første
                    LocalTime randomStartTime2;
                    do {
                        // Laver tilfældig tid mellem 10:00 og 20:59
                        randomStartTime2 = LocalTime.of(random.nextInt(10) + 10, random.nextInt(60));
                        // Sikre der er mindst 30 min i mellem
                    } while (Math.abs(randomStartTime2.toSecondOfDay() - endTime1.toSecondOfDay()) < 1800);

                    LocalTime endTime2 = randomStartTime2.plusMinutes(duration);

                    // Laver den anden showtime
                    // Laver tilfældig showtime for den anden showtime
                    TheaterModel randomTheater2 = theaters.get(random.nextInt(theaters.size()));
                    ShowtimeModel showtime2 = new ShowtimeModel();
                    // Laver tilfældig dato inden for 30 dage
                    showtime2.setMovieDate(today.plusDays(random.nextInt(30)));
                    showtime2.setStartTime(randomStartTime2);
                    showtime2.setEndTime(endTime2);
                    // Laver tilfældig pris mellem 75 og 100
                    showtime2.setPrice(75.0 + random.nextDouble() * 25.0);
                    showtime2.setMovie(movie);
                    showtime2.setTheater(randomTheater2);

                    // Gemmer den anden showtime
                    showtimeRepository.save(showtime2);
                }
            }
        }
    }
}
