package stud.kea.dk.biografbackend.bookedChair.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stud.kea.dk.biografbackend.chair.model.ChairModel;
import stud.kea.dk.biografbackend.reservation.model.ReservationModel;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookedChairModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chair_id", nullable = false)
    private ChairModel chair;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "showtime_id", nullable = false)
    private ShowtimeModel showtime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_id", nullable = false)
    private ReservationModel reservation;
}
