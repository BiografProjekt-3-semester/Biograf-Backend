package stud.kea.dk.biografbackend.movie.model;
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


    //@OneToMany(mappedBy = "movie")
    //private List<ShowTime> showTimes;

}
