package stud.kea.dk.biografbackend.showtime.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShowtimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate movieDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Movie_id", referencedColumnName = "id")
    private MovieModel movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", referencedColumnName = "id")
    private TheaterModel theaterID;


    public ShowtimeModel(int id, LocalDate movieDate, LocalTime startTime, double price, LocalTime endTime) {
        this.id = id;
        this.movieDate = movieDate;
        this.startTime = startTime;
        this.price = price;
        this.endTime = endTime;
    }
}
