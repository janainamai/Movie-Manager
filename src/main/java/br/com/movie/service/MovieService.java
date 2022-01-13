package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Movie;
import br.com.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private static final String MOVIE_NOT_FOUND = "Movie not found";

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> list() {
        return movieRepository.findAll();
    }

    public List<Movie> findByTitle(String title) {
        List<Movie> list = movieRepository.findByTitleIgnoreCase(title);

        if (list.isEmpty()) {
            throw new BadRequestException(MOVIE_NOT_FOUND);
        } else {
            return list;
        }
    }

    public Movie save(Movie movie) {
        if (!validToSave(movie)) {
            throw new BadRequestException("A movie with this name, language and type already exists");
        }
        return movieRepository.save(movie);
    }

    public Movie replace(Movie movie) {
        if (movieRepository.existsById(movie.getId()) && validToReplace(movie)) {
            return movieRepository.save(movie);
        } else {
            throw new BadRequestException(MOVIE_NOT_FOUND);
        }
    }

    /**
     * Validates if a movie with this title, language and type already exists.
     *
     * @param movie Movie to be validated
     * @return false if already exists and true if not
     */
    private boolean validToSave(Movie movie) {
        List<Movie> list = movieRepository.findByTitleIgnoreCase(movie.getTitle());

        if (!list.isEmpty()) {
            for (Movie mov : list) {
                if (movie.getLanguage().equals(mov.getLanguage())
                        && movie.getMovieType().equals(mov.getMovieType())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Validates if a movie with this title, language and type already exists.
     *
     * @param movie Movie to be validated
     * @return false if already exists and true if not
     */
    private boolean validToReplace(Movie movie) {
        List<Movie> list = movieRepository.findByTitleIgnoreCase(movie.getTitle());

        if (!list.isEmpty()) {
            for (Movie data : list) {
                if (movie.getLanguage().equals(data.getLanguage())
                        && movie.getMovieType().equals(data.getMovieType())
                        && movie.getId() != data.getId()) {
                    return false;
                }
            }
        }
        return true;
    }
}