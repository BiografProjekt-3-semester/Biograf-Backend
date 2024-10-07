package stud.kea.dk.biografbackend.theater.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.chair.model.ChairModel;
import stud.kea.dk.biografbackend.theater.service.TheaterService;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    final private TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping("/{theaterId}/chairs")
    public ResponseEntity<String> addChairsToTheater(@PathVariable int theaterId, @RequestBody List<ChairModel> chairs) {
        try {
            theaterService.addChairsToTheater(theaterId, chairs);
            return new ResponseEntity<>("Chairs added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
