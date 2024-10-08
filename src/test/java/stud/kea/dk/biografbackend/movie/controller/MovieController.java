package stud.kea.dk.biografbackend.movie.controller;

import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.service.ApiServiceGetMovie;
import stud.kea.dk.biografbackend.movie.service.MovieCRUD;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/movie")
public class MovieController {

    final private MovieCRUD movieCRUD;
    final private ApiServiceGetMovie apiServiceGetMovie;

    public MovieController(MovieCRUD movieCRUD, ApiServiceGetMovie apiServiceGetMovie){
        this.movieCRUD = movieCRUD;
        this.apiServiceGetMovie = apiServiceGetMovie;
    }

    @PostMapping("/makeMovie")
    public MovieModel makeMovie(@RequestBody MovieModel movie) {
        return movieCRUD.createMovie(movie);

    }

    @GetMapping("/getAllMovies")
    public List<MovieModel> getAllMovies() {
        return movieCRUD.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieModel getMovieById(@PathVariable int id) {
        return movieCRUD.getMovieById(id);
    }

    @PutMapping("/{id}")
    public MovieModel updateMovie(@PathVariable int id, @RequestBody MovieModel movie) {
        movie.setId(id);
        return movieCRUD.updateMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieCRUD.deleteMovie(id);
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
