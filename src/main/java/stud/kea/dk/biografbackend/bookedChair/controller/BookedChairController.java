package stud.kea.dk.biografbackend.bookedChair.controller;


import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.bookedChair.model.BookedChairModel;
import stud.kea.dk.biografbackend.bookedChair.service.BookedChairService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/bookedchair")
public class BookedChairController {

    private final BookedChairService bookedChairService;

    public BookedChairController(BookedChairService bookedChairService) {
        this.bookedChairService = bookedChairService;
    }

    @PostMapping
    public BookedChairModel createBookedChair(@RequestBody BookedChairModel bookedChairModel) {
        return bookedChairService.createBookedChair(bookedChairModel);
    }

    @GetMapping("/showtime/{showtimeId}")
    public List<BookedChairModel> getBookedChairsByShowtime(@PathVariable int showtimeId) {
        return bookedChairService.getBookedChairsByShowtime(showtimeId);
    }

    @GetMapping("/{id}")
    public BookedChairModel getBookedChair(@PathVariable int id) {
        return bookedChairService.getBookedChairById(id);
    }
}
