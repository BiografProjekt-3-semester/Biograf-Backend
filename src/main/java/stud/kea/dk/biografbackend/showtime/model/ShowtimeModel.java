package stud.kea.dk.biografbackend.showtime.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    //@ManyToOne
    // int movieid;
    //@JoinColumn(name = "MovieModel", referencedColumnName = "Movieid")

    //@ManyToOne
    //int theaterid;
    //@JoinColumn(name = "TheaterModel", referencedColumnName = "theaterID")
    LocalDate movieDate;
    LocalTime startTime;
    LocalTime endTime;
    double price;


}
