package stud.kea.dk.biografbackend.movie.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

// Load the full application context
@SpringBootTest
// Use the real database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Reset the database between tests to avoid state issues
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MovieServiceIntegrationTestOld {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        // Optionally clear database before each test if needed
        movieRepository.deleteAll();
    }

    @Test
    void deleteMovie_ShouldDeleteMovieFromDatabase() {
        // Arrange: Create and save a movie
        MovieModel movie = new MovieModel(0, 15, 120, "Test Movie", "A test description", "test-picture.jpg");
        MovieModel savedMovie = movieRepository.save(movie);
        int movieId = savedMovie.getId();

        // Act: Delete the movie by its ID
        movieService.deleteMovie(movieId);

        // Assert: Verify the movie is no longer in the database
        Optional<MovieModel> deletedMovie = movieRepository.findById(movieId);
        assertFalse(deletedMovie.isPresent(), "Movie should be deleted from the database");
    }
}
