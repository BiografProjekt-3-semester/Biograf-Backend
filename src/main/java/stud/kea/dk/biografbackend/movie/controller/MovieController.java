package stud.kea.dk.biografbackend.movie.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;
import stud.kea.dk.biografbackend.movie.service.ApiServiceGetMovie;
import stud.kea.dk.biografbackend.movie.service.MovieCRUD;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/movie")
public class MovieController {

    final private MovieCRUD movieCRUD;
    final private ApiServiceGetMovie apiServiceGetMovie;
    final private MovieRepository movieRepository;

    public MovieController(MovieCRUD movieCRUD, ApiServiceGetMovie apiServiceGetMovie, MovieRepository movieRepository){
        this.movieCRUD = movieCRUD;
        this.apiServiceGetMovie = apiServiceGetMovie;
        this.movieRepository = movieRepository;
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

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieCRUD.deleteMovie(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MovieModel> updateMovie(@PathVariable int id, @RequestBody MovieModel movie) {
        MovieModel updatedMovie = movieCRUD.updateMovie(id, movie);
        return ResponseEntity.ok(updatedMovie);
    }

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
