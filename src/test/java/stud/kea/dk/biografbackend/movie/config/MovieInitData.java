package stud.kea.dk.biografbackend.movie.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;
import stud.kea.dk.biografbackend.movie.service.ApiServiceGetMovie;

@Component
@Order(3)
public class MovieInitData implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final ApiServiceGetMovie apiServiceGetMovie;

    public MovieInitData(MovieRepository movieRepository, ApiServiceGetMovie apiServiceGetMovie) {
        this.movieRepository = movieRepository;
        this.apiServiceGetMovie=apiServiceGetMovie;
    }

    @Override
    public void run(String... args) throws Exception {

        if(movieRepository.findAll().isEmpty()) {
            apiServiceGetMovie.getMoviesFromAPIByPage(1);
        }
    }
}
