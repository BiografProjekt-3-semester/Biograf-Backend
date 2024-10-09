package stud.kea.dk.biografbackend.movie.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;
import stud.kea.dk.biografbackend.movie.service.ApiServiceGetMovie;
import stud.kea.dk.biografbackend.movie.service.MovieCRUD;

import java.util.List;

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
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteMovieConfirmed(@PathVariable int id) {
        String result = movieCRUD.deleteMovieConfirmed(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/{id}/check-before-delete")
    public ResponseEntity<String> checkForShowtimes(@PathVariable int id) {
        String result = movieCRUD.checkForShowtimesBeforeDelete(id);

        if (result.contains("visningstider")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
