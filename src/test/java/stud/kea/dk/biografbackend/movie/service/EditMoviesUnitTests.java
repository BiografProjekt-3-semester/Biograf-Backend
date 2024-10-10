package stud.kea.dk.biografbackend.movie.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EditMoviesUnitTests {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private MovieModel existingMovie;
    private MovieModel updatedMovieData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set up existing movie
        existingMovie = new MovieModel();
        existingMovie.setId(1);
        existingMovie.setTitle("Old Movie Title");
        existingMovie.setDescription("Old Description");
        existingMovie.setDurationEkstra("120 min");

        // Set up updated movie data
        updatedMovieData = new MovieModel();
        updatedMovieData.setTitle("Updated Movie Title");
        updatedMovieData.setDescription("Updated Description");
        updatedMovieData.setDurationEkstra("130 min");
    }

    @Test
    void testUpdateMovie_Success() {
        // Arrange
        when(movieRepository.findById(1)).thenReturn(Optional.of(existingMovie));
        when(movieRepository.save(any(MovieModel.class))).thenReturn(existingMovie);

        // Act
        MovieModel updatedMovie = movieService.updateMovie(1, updatedMovieData);

        // Assert
        assertNotNull(updatedMovie);
        assertEquals("Updated Movie Title", updatedMovie.getTitle());
        assertEquals("Updated Description", updatedMovie.getDescription());
        assertEquals("130 min", updatedMovie.getDurationEkstra());

        // Verify that movieRepository.save was called
        Mockito.verify(movieRepository).save(existingMovie);
    }

    @Test
    void testUpdateMovie_NotFound() {
        // Arrange
        when(movieRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> movieService.updateMovie(1, updatedMovieData));

        String expectedMessage = "Movie not found with id 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        // Verify that movieRepository.save was not called
        Mockito.verify(movieRepository, Mockito.never()).save(any(MovieModel.class));
    }

    // En test der sikre at servicen håndtere null værdier
    @Test
    void testUpdateMovie_NullInput() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> movieService.updateMovie(1, null));

        String expectedMessage = "Movie data cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateMovie_EmptyTitle() {
        // Arrange
        MovieModel invalidMovie = new MovieModel();
        invalidMovie.setTitle("");  // Empty title
        invalidMovie.setDescription("Updated Description");

        when(movieRepository.findById(1)).thenReturn(Optional.of(existingMovie));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            movieService.updateMovie(1, invalidMovie);
        });

        String expectedMessage = "Movie title cannot be empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateMovie_InvalidId() {
        // Arrange
        int invalidId = -1;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            movieService.updateMovie(invalidId, updatedMovieData);
        });

        String expectedMessage = "Invalid movie ID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testUpdateMovie_ExcessivelyLongTitle() {
        // Arrange
        String longTitle = "A".repeat(300);  // Simulating a title with 300 characters
        updatedMovieData.setTitle(longTitle);

        when(movieRepository.findById(1)).thenReturn(Optional.of(existingMovie));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            movieService.updateMovie(1, updatedMovieData);
        });

        String expectedMessage = "Movie title cannot exceed 255 characters";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateMovie_SpecialCharactersInTitle() {
        // Arrange
        updatedMovieData.setTitle("Movie @#%!");

        when(movieRepository.findById(1)).thenReturn(Optional.of(existingMovie));
        when(movieRepository.save(any(MovieModel.class))).thenReturn(existingMovie);

        // Act
        MovieModel updatedMovie = movieService.updateMovie(1, updatedMovieData);

        // Assert
        assertEquals("Movie @#%!", updatedMovie.getTitle());
    }
}
