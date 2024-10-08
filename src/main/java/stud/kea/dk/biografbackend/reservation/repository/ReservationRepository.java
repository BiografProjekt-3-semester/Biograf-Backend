package stud.kea.dk.biografbackend.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.biografbackend.reservation.model.ReservationModel;

public interface ReservationRepository extends JpaRepository<ReservationModel, Integer> {
}
