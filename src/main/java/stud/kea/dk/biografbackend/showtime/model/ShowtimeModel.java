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

    @ManyToOne
    @JoinColumn(name = "MovieModel", referencedColumnName = "id")
    MovieModel movie;

    @ManyToOne
    @JoinColumn(name = "TheaterModel", referencedColumnName = "id")
    TheaterModel theaterModel;

    LocalDate movieDate;
    LocalTime startTime;
    LocalTime endTime;
    double price;
    int theaterid;
    int movieid;

}
