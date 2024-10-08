package stud.kea.dk.biografbackend.reservation.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.reservation.model.ReservationModel;
import stud.kea.dk.biografbackend.reservation.repository.ReservationRepository;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.showtime.repository.ShowtimeRepository;
import stud.kea.dk.biografbackend.customer.model.CustomerModel;
import stud.kea.dk.biografbackend.customer.repository.CustomerRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ShowtimeRepository showtimeRepository;
    private final CustomerRepository customerRepository;

    public ReservationService(ReservationRepository reservationRepository, ShowtimeRepository showtimeRepository, CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.showtimeRepository = showtimeRepository;
        this.customerRepository = customerRepository;
    }

    public List<ReservationModel> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<ReservationModel> findById(int id) {
        return reservationRepository.findById(id);
    }

    public ReservationModel createReservation(int customerId, int showtimeId, int price) {
        CustomerModel customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        ShowtimeModel showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new NoSuchElementException("Showtime not found"));

        ReservationModel reservation = new ReservationModel();
        reservation.setCustomer(customer);
        reservation.setShowtime(showtime);
        reservation.setPrice(price);

        return reservationRepository.save(reservation);
    }

    public Optional<ReservationModel> updateReservation(int id, ReservationModel updatedReservation) {
        Optional<ReservationModel> reservationOptional = reservationRepository.findById(id);

        if (reservationOptional.isPresent()) {
            ReservationModel existingReservation = reservationOptional.get();
            existingReservation.setPrice(updatedReservation.getPrice());
            existingReservation.setCustomer(updatedReservation.getCustomer());
            existingReservation.setShowtime(updatedReservation.getShowtime());

            return Optional.of(reservationRepository.save(existingReservation));
        }
        return Optional.empty();
    }

    public boolean deleteById(int id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
