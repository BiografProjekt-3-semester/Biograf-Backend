package stud.kea.dk.biografbackend.showtime.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.showtime.repository.ShowtimeRepository;
import stud.kea.dk.biografbackend.showtime.service.ApiServiceGetShowtimeImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/showTimes")
public class ShowtimeController {
    
    final private ApiServiceGetShowtimeImpl showtimeService;
    private ShowtimeRepository showtimeRepository;


    public ShowtimeController(ApiServiceGetShowtimeImpl apiServiceGetShowtime) {
        this.showtimeService = apiServiceGetShowtime;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createShowtime(@RequestBody ShowtimeModel showtime) {
        if (!showtimeService.isShowtimeValid(showtime)) {
            return ResponseEntity.badRequest()
                    .body("Showtime overlapper med en anden forestilling eller der er ikke nok tid til rengøring.");
        }
        ShowtimeModel createdShowtime = showtimeService.createShowtime(showtime);
        return new ResponseEntity<>(createdShowtime, HttpStatus.CREATED);
    }
    // GET-anmodning for at hente visningstider for en specifik film baseret på filmens ID
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowtimeModel>> getShowTimesByMovieId(
            @PathVariable int movieId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date) {

        // Delete expired showtimes before fetching
        deleteExpiredShowtimes();

        // Fetch showtimes, optionally filtering by the provided date
        List<ShowtimeModel> showTimes;
        if (date.isPresent()) {
            showTimes = showtimeService.getShowTimesByMovieIdAndDate(movieId, date.get());
        } else {
            showTimes = showtimeService.getShowTimesByMovieId(movieId);
        }

        return new ResponseEntity<>(showTimes, HttpStatus.OK);
    }

    @DeleteMapping("/deleteExpired")
    public ResponseEntity<?> deleteExpiredShowtimes() {
        List<ShowtimeModel> deletedShowtimes = showtimeService.deleteExpiredShowtimes();

        if (deletedShowtimes.isEmpty()) {
            return new ResponseEntity<>("Ingen forældede visningstider fundet.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Forældede visningstider slettet.", HttpStatus.OK);
    }
    @GetMapping
    public List<ShowtimeModel> getAllShowtimes() {
        return showtimeService.getAllShowtimes();
    }

    @GetMapping("/{showtimeId}")
    public ResponseEntity<ShowtimeModel> getShowtimeById(@PathVariable int showtimeId) {
        ShowtimeModel showtime = showtimeService.getShowtimeById(showtimeId);
        if (showtime != null) {
            return new ResponseEntity<>(showtime, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}