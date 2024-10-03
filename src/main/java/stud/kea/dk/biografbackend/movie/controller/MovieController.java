package stud.kea.dk.biografbackend.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.service.ApiServiceGetMovie;
import stud.kea.dk.biografbackend.movie.service.ApiServiceGetMovieImpl;
import stud.kea.dk.biografbackend.movie.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    ApiServiceGetMovie apiServiceGetMovie;

    @PostMapping
    public MovieModel createMovie(@RequestBody MovieModel movie) {
        return movieService.createMovie(movie);
    }

    @GetMapping
    public List<MovieModel> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieModel getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @PutMapping("/{id}")
    public MovieModel updateMovie(@PathVariable int id, @RequestBody MovieModel movie) {
        movie.setId(id);
        return movieService.updateMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }


    // Denne metode håndterer en GET-anmodning med et filmnavn
    @GetMapping("/search/{name}")
    public List<MovieModel> getMovieByName(@PathVariable String name) {
        return apiServiceGetMovie.getMoviesFromAPI(name);
    }

    @GetMapping("/page/{page}")
    public List<MovieModel> getMoviesFromAPIByPage(Integer page) {
        apiServiceGetMovie.getMoviesFromAPIByPage(page);
        return null;
    }

}
