package stud.kea.dk.biografbackend.movie.service;

import stud.kea.dk.biografbackend.movie.model.MovieModel;

import java.util.List;

public interface MovieCRUD {
    public MovieModel createMovie(MovieModel movie);
    public List<MovieModel> getAllMovies();
    public MovieModel getMovieById(int id);

    public MovieModel updateMovie(MovieModel movie);

    public void deleteMovie(int id);
}
