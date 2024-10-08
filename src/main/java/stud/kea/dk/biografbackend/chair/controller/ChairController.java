package stud.kea.dk.biografbackend.chair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.chair.model.ChairModel;
import stud.kea.dk.biografbackend.chair.repository.ChairRepository;
import stud.kea.dk.biografbackend.chair.service.ChairService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/chairs")
public class ChairController {

    @Autowired
    private ChairRepository chairRepository;

    @GetMapping("/theater/{theaterId}")
    public List<ChairModel> getChairsByTheater(@PathVariable("theaterId") int theaterId) {
        return chairRepository.findByTheaterId(theaterId);
    }
    @PutMapping("/{id}/reserve")
    public ResponseEntity<String> reserveChair(@PathVariable int id) {
        Optional<ChairModel> chairOpt = chairRepository.findById(id);
        if (chairOpt.isPresent()) {
            ChairModel chair = chairOpt.get();
            chairRepository.save(chair);
            return ResponseEntity.ok("Sæde reserveret");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sæde ikke fundet");
    }

}
