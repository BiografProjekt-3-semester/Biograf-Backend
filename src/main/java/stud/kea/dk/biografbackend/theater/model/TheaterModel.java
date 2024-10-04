package stud.kea.dk.biografbackend.theater.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TheaterModel {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   String name;


    @OneToMany(mappedBy = "theaterID", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonBackReference
    private List<ShowtimeModel> showtimes = new ArrayList<>();

    public TheaterModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
