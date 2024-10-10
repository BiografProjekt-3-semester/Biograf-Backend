package stud.kea.dk.biografbackend.movie.service;

import stud.kea.dk.biografbackend.movie.model.MovieModel;

import java.util.List;

public interface MovieCRUD {
    public MovieModel createMovie(MovieModel movie);
    public List<MovieModel> getAllMovies();
    public MovieModel getMovieById(int id);

    public MovieModel updateMovie(int id, MovieModel movie);

    public String checkForShowtimesBeforeDelete(int movieid);

    public String deleteMovieConfirmed(int movieid);

}
