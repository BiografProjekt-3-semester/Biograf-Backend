package stud.kea.dk.biografbackend.movie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;
@Component
public class InititData implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public void run(String... args) throws Exception {
/*
        MovieModel movie1 = new MovieModel(1, 13, 148, "Inception", "A mind-bending thriller", "inception.jpg");
        MovieModel movie2 = new MovieModel(2, 15, 136, "The Matrix", "A hacker discovers the real world", "matrix.jpg");
        MovieModel movie3 = new MovieModel(3, 7, 102, "Frozen", "An animated tale of two sisters", "frozen.jpg");
        MovieModel movie4 = new MovieModel(4, 13, 169, "Interstellar", "A team of explorers travel through a wormhole in space", "interstellar.jpg");
        MovieModel movie5 = new MovieModel(5, 15, 152, "The Dark Knight", "Batman faces the Joker in Gotham City", "darkknight.jpg");
        MovieModel movie6 = new MovieModel(6, 12, 162, "Avatar", "A paraplegic Marine on an alien planet", "avatar.jpg");
        MovieModel movie7 = new MovieModel(7, 6, 88, "The Lion King", "A young lion prince flees his kingdom", "lionking.jpg");
        MovieModel movie8 = new MovieModel(8, 12, 127, "Jurassic Park", "Dinosaurs run amok in a theme park", "jurassicpark.jpg");
        MovieModel movie9 = new MovieModel(9, 12, 195, "Titanic", "A young couple falls in love aboard the ill-fated ship", "titanic.jpg");
        MovieModel movie10 = new MovieModel(10, 18, 154, "Pulp Fiction", "The lives of two hitmen intertwine with other characters", "pulpfiction.jpg");
        MovieModel movie11 = new MovieModel(11, 16, 175, "The Godfather", "The story of a powerful mafia family", "godfather.jpg");
        MovieModel movie12 = new MovieModel(12, 6, 100, "Finding Nemo", "A clownfish searches for his missing son", "findingnemo.jpg");
        MovieModel movie13 = new MovieModel(13, 10, 152, "Harry Potter and the Sorcerer's Stone", "A boy discovers he is a wizard", "harrypotter.jpg");

        movieRepository.save(movie1);


 */

    }
}
