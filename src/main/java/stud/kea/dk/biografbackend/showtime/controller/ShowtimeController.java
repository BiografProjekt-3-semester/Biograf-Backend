package stud.kea.dk.biografbackend.showtime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.showtime.service.ApiServiceGetShowtimeImpl;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
public class ShowtimeController {

    @Autowired
    private ApiServiceGetShowtimeImpl showtimeService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ShowtimeModel> createShowtime(@RequestBody ShowtimeModel showtime) {
        ShowtimeModel createdShowtime = showtimeService.createShowtime(showtime);
        return new ResponseEntity<>(createdShowtime, HttpStatus.CREATED);
    }
    // GET-anmodning for at hente visningstider for en specifik film baseret på filmens ID
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowtimeModel>> getShowtimesByMovieId(@PathVariable int movieId) {
        List<ShowtimeModel> showtimes = showtimeService.getShowtimesByMovieId(movieId);
        return new ResponseEntity<>(showtimes, HttpStatus.OK);
    }



}
