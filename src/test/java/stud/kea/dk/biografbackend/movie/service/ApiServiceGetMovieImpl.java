package stud.kea.dk.biografbackend.movie.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stud.kea.dk.biografbackend.movie.model.MovieModel;
import stud.kea.dk.biografbackend.movie.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceGetMovieImpl implements ApiServiceGetMovie {

    final private MovieRepository movieRepository;

    public ApiServiceGetMovieImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private final String API_KEY = "d83ad2f6";  // Din API-nøgle
    private final String BASE_URL = "http://www.omdbapi.com/?t=%s&apikey=%s";
    private final String BASE_URL_PAGE = "http://www.omdbapi.com/";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<MovieModel> getMoviesFromAPI(String name) {
        List<MovieModel> movieList = new ArrayList<>();

        try {
            // Lav API-anmodningen
            String url = String.format(BASE_URL, name, API_KEY);
            String response = restTemplate.getForObject(url, String.class);

            // Parse JSON-responsen
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);

            // Tjek om filmen blev fundet
            if (rootNode.has("Response") && rootNode.get("Response").asText().equals("True")) {
                // Lav et nyt MovieModel-objekt
                MovieModel movie = new MovieModel();
                movie.setTitle(rootNode.get("Title").asText());
                //movie.setAgeLimit(Integer.parseInt(rootNode.get("age").asText()));
                movie.setDescription(rootNode.get("Genre").asText());
                movie.setDurationEkstra(rootNode.get("Runtime").asText());
                movie.setPicture(rootNode.get("Poster").asText());
                movieRepository.save(movie);


                // Tilføj filmen til listen
                movieList.add(movie);
            } else {
                System.out.println("Ingen film fundet for søgning: " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movieList;
    }
    public void getMoviesFromAPIByPage(Integer page) {
        List<MovieModel> movieList = new ArrayList<>();

        try {
            // Fast søgeord 'movie' for at få generelle resultater
            String searchQuery = "movie";
            // Lav API-anmodningen for en specifik side
            String url = String.format(BASE_URL_PAGE + "?s=movie&type=movie&page=" + page + "&apikey=" + API_KEY);
            String response = restTemplate.getForObject(url, String.class);

            // Parse JSON-responsen
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);

            // Tjek om API'et returnerede gyldige resultater
            if (rootNode.has("Response") && rootNode.get("Response").asText().equals("True")) {
                JsonNode searchResults = rootNode.get("Search");

                // Kontrollér, om "Search"-feltet er til stede og ikke null
                if (searchResults != null && searchResults.isArray()) {
                    // Loop gennem hvert filmresultat og tilføj dem til listen
                    for (JsonNode movieNode : searchResults) {
                        MovieModel movie = new MovieModel();
                        String title = movieNode.get("Title").asText();

                        // Lav et API-kald for at få fulde filmoplysninger inkl. Runtime
                        String movieDetailsUrl = String.format(BASE_URL, title, API_KEY);
                        String movieDetailsResponse = restTemplate.getForObject(movieDetailsUrl, String.class);
                        JsonNode movieDetailsNode = mapper.readTree(movieDetailsResponse);

                        // Udfyld felter fra de detaljerede oplysninger
                        movie.setTitle(title);
                        movie.setDescription(movieDetailsNode.has("Genre") ? movieDetailsNode.get("Genre").asText() : "N/A");
                        movie.setDurationEkstra(movieDetailsNode.has("Runtime") ? movieDetailsNode.get("Runtime").asText() : "N/A");
                        movie.setPicture(movieDetailsNode.has("Poster") ? movieDetailsNode.get("Poster").asText() : "N/A");

                        movieRepository.save(movie);

                    }
                } else {
                    System.out.println("Ingen resultater fundet på side: " + page);
                }
            } else {
                System.out.println("Ingen resultater fundet for forespørgsel.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
