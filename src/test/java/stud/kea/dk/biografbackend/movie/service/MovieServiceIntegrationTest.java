package stud.kea.dk.biografbackend.movie.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional // This ensures the test operates within a transaction and rolls back at the end
class MovieServiceIntegrationTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        // Set up a movie with id x for testing
        MovieModel movie = new MovieModel(11, 18, 120, "Test Movie", "A test movie description", "test.jpg");
        movieRepository.save(movie);
    }

    @Test
    void deleteMovie_ShouldDeleteMovieWithId1() {
        // Arrange: ensure the movie with id x exists
        assertTrue(movieRepository.existsById(10));

        // Act: delete the movie with id x
        movieService.deleteMovie(10);

        // Assert: movie with id x should no longer exist
        assertFalse(movieRepository.existsById(10));
    }
}
