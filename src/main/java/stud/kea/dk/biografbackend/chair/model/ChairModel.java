package stud.kea.dk.biografbackend.chair.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    // @ManyToOne
    // @JoinColumn(name = "id.theater", nullable = false)
   // private Theater theater
}
