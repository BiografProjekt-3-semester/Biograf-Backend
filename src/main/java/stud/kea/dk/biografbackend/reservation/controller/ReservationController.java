package stud.kea.dk.biografbackend.reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.reservation.model.ReservationModel;
import stud.kea.dk.biografbackend.reservation.service.ReservationService;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.customer.model.CustomerModel;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationModel> createReservation(@RequestBody ReservationModel reservationModel) {
        try {
            ReservationModel createdReservation = reservationService.createReservation(
                    reservationModel.getCustomer().getId(),
                    reservationModel.getShowtime().getId(),
                    reservationModel.getPrice()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Hent alle reservationer
    @GetMapping
    public ResponseEntity<List<ReservationModel>> getAllReservations() {
        List<ReservationModel> reservations = reservationService.findAll();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    // Hent en specifik reservation via ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservationModel> getReservationById(@PathVariable int id) {
        Optional<ReservationModel> reservation = reservationService.findById(id);
        return reservation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Opdater en eksisterende reservation
    @PutMapping("/{id}")
    public ResponseEntity<ReservationModel> updateReservation(
            @PathVariable int id,
            @RequestBody ReservationModel updatedReservation) {
        Optional<ReservationModel> reservation = reservationService.updateReservation(id, updatedReservation);
        return reservation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Slet en reservation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable int id) {
        boolean isDeleted = reservationService.deleteById(id);
        if (isDeleted) {
            return new ResponseEntity<>("Reservation deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        }
    }
}
