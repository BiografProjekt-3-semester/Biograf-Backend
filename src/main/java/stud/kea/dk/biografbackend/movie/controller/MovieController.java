package stud.kea.dk.biografbackend.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.service.ApiServiceGetMovie;
import stud.kea.dk.biografbackend.movie.service.ApiServiceGetMovieImpl;
import stud.kea.dk.biografbackend.movie.service.MovieCRUD;
import stud.kea.dk.biografbackend.movie.service.MovieService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieCRUD movieCRUD;
    @Autowired
    ApiServiceGetMovie apiServiceGetMovie;

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
