package stud.kea.dk.biografbackend.showtime.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.theater.model.TheaterModel;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.showtime.repository.ShowtimeRepository;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;
import stud.kea.dk.biografbackend.theater.repository.TheaterRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApiServiceGetShowtimeImplTest {

    @Mock
    private ShowtimeRepository showtimeRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private TheaterRepository theaterRepository;

    @InjectMocks
    private ApiServiceGetShowtimeImpl apiServiceGetShowtimeImpl;

    private MovieModel movie;
    private TheaterModel theater;
    private ShowtimeModel showtime;

    @BeforeEach
    void setUp() {
        // Opret nødvendige objekter for testene
        movie = new MovieModel(1, 18, 120, "Avengers", "Action movie", "avengers.jpg");
        theater = new TheaterModel(1, "Theater 1");
        showtime = new ShowtimeModel(1, LocalDate.now(), LocalTime.of(14, 0),100.0, LocalTime.of(16, 0));
        showtime.setMovie(movie);
        showtime.setTheater(theater);
    }

    @Test
    void testCreateShowtime_Success() {
        // Arrange
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        when(theaterRepository.findById(1)).thenReturn(Optional.of(theater));
        when(showtimeRepository.save(showtime)).thenReturn(showtime);

        // Act
        ShowtimeModel result = apiServiceGetShowtimeImpl.createShowtime(showtime);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Avengers", result.getMovie().getTitle());
        assertEquals("Theater 1", result.getTheater().getName());
    }

    @Test
    void testCreateShowtime_MovieNotFound() {
        // Arrange
        when(movieRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            apiServiceGetShowtimeImpl.createShowtime(showtime);
        });
        assertEquals("Movie not found", exception.getMessage());
    }

    @Test
    void testCreateShowtime_TheaterNotFound() {
        // Arrange
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        when(theaterRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            apiServiceGetShowtimeImpl.createShowtime(showtime);
        });
        assertEquals("Theater not found", exception.getMessage());
    }
    @Test
    void testGetShowTimesByMovieId() {
        // Arrange
        ShowtimeModel showtime1 = new ShowtimeModel(1, LocalDate.now(), LocalTime.of(10, 0),100.0, LocalTime.of(12, 0));
        ShowtimeModel showtime2 = new ShowtimeModel(2, LocalDate.now(), LocalTime.of(12, 30), 120.0, LocalTime.of(14, 30));
        when(showtimeRepository.findShowtimeByMovieIdOrderByMovieDateAscStartTimeAsc(1))
                .thenReturn(List.of(showtime1, showtime2));

        // Act
        List<ShowtimeModel> result = apiServiceGetShowtimeImpl.getShowTimesByMovieId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
    }

    @Test
    void testDeleteExpiredShowtimes() {
        // Arrange
        ShowtimeModel expiredShowtime = new ShowtimeModel(1, LocalDate.now().minusDays(1), LocalTime.of(10, 0),100.0, LocalTime.of(12, 0));
        ShowtimeModel todayExpiredShowtime = new ShowtimeModel(2, LocalDate.now(), LocalTime.of(9, 0),120.0, LocalTime.of(11, 0));

        when(showtimeRepository.findByMovieDateBefore(LocalDate.now())).thenReturn(List.of(expiredShowtime));
        when(showtimeRepository.findByMovieDateAndStartTimeBefore(LocalDate.now(), LocalTime.now())).thenReturn(List.of(todayExpiredShowtime));

        // Act
        List<ShowtimeModel> result = apiServiceGetShowtimeImpl.deleteExpiredShowtimes();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(showtimeRepository).deleteAll(result);
    }

    @Test
    void testIsShowtimeValid() {
        // Arrange
        ShowtimeModel existingShowtime = new ShowtimeModel(1, LocalDate.now(), LocalTime.of(10, 0), 100.0, LocalTime.of(12, 0));
        ShowtimeModel newShowtime = new ShowtimeModel(2, LocalDate.now(), LocalTime.of(11, 0),120.0, LocalTime.of(13, 0));

        when(showtimeRepository.findByTheaterAndMovieDate(existingShowtime.getTheater(), existingShowtime.getMovieDate()))
                .thenReturn(List.of(existingShowtime));

        // Act
        boolean result = apiServiceGetShowtimeImpl.isShowtimeValid(newShowtime);

        // Assert
        assertFalse(result);  // Det skal returnere false, da tiderne overlapper
    }
}
