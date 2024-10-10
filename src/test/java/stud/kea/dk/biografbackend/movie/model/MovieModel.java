package stud.kea.dk.biografbackend.movie.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MovieModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int ageLimit;
    private int duration;
    private String durationEkstra;
    private String title;
    private String description;
    private String picture;


    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonBackReference
    private List<ShowtimeModel> showTimes;

    public MovieModel(int id, int ageLimit, int duration, String title, String description, String picture) {
        this.id = id;
        this.ageLimit = ageLimit;
        this.duration = duration;
        this.title = title;
        this.description = description;
        this.picture = picture;
    }
}