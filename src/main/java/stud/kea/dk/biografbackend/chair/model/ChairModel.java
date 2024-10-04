package stud.kea.dk.biografbackend.chair.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChairModel {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int chairNr;
    private int rowNr;
    private boolean isAvailable;
    private boolean isSpecial;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)

    private TheaterModel theater;
}
