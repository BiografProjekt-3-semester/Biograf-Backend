package stud.kea.dk.biografbackend.movie.model;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;

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

    @Column(updatable = false)  // createdAt kan ikke opdateres, når først det er sat
    private LocalDateTime createdAt;

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

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();  // Sæt oprettelsestidspunktet automatisk, når filmen oprettes
    }
}