package stud.kea.dk.biografbackend.showtime.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.showtime.service.ApiServiceGetShowtimeImpl;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/showTimes")
public class ShowtimeController {

    final private ApiServiceGetShowtimeImpl showtimeService;

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
    public ResponseEntity<List<ShowtimeModel>> getShowTimesByMovieId(@PathVariable int movieId) {
        deleteExpiredShowTimes();
        List<ShowtimeModel> showTimes = showtimeService.getShowTimesByMovieId(movieId);
        return new ResponseEntity<>(showTimes, HttpStatus.OK);
    }

    @DeleteMapping("/deleteExpired")
    public ResponseEntity<?> deleteExpiredShowTimes() {
        List<ShowtimeModel> deletedShowTimes = showtimeService.deleteExpiredShowtimes();

        if (deletedShowTimes.isEmpty()) {
            return new ResponseEntity<>("Ingen forældede visningstider fundet.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Forældede visningstider slettet.", HttpStatus.OK);
    }
    @GetMapping
    public List<ShowtimeModel> getAllShowtimes() {
        return showtimeService.getAllShowtimes();
    }

    // En Patch Mapping til at opdatere en eksisterende showtime
    @PatchMapping("/{id}")
    public ResponseEntity<ShowtimeModel> updateShowtime(@PathVariable Integer id, @RequestBody ShowtimeModel updatedShowtime) {
        try {
            ShowtimeModel existingShowtime = showtimeService.getShowtimeById(id);
            if (existingShowtime == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Opdatere de relevante fields
            existingShowtime.setMovieDate(updatedShowtime.getMovieDate());
            existingShowtime.setStartTime(updatedShowtime.getStartTime());
            existingShowtime.setEndTime(updatedShowtime.getEndTime());
            existingShowtime.setPrice(updatedShowtime.getPrice());

            // Gemmer den opdaterede showtime i databasen
            showtimeService.saveShowtime(existingShowtime);

            return new ResponseEntity<>(existingShowtime, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
