package stud.kea.dk.biografbackend.movie.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteMovie_ShouldCallDeleteById() {
        // Arrange
        int movieId = 10;

        // Act
        movieService.deleteMovie(movieId);

        // Assert
        verify(movieRepository, times(1)).deleteById(movieId);
    }
}
