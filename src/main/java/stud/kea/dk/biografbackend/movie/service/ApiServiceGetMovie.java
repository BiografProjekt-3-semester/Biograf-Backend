package stud.kea.dk.biografbackend.movie.service;

import stud.kea.dk.biografbackend.movie.model.MovieModel;

import java.util.List;

public interface ApiServiceGetMovie {

    public List<MovieModel> getMoviesFromAPI(String name);

    public void getMoviesFromAPIByPage(Integer page);
}
