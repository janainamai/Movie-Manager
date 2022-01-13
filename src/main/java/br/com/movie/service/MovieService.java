package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Movie;
import br.com.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie save(Movie movie) {
        if (!valid(movie)) {
            throw new BadRequestException("A movie with this name, language and type already exists");
        }
        return movieRepository.save(movie);
    }

    /**
     * Validates if a movie with this title, language and type already exists.
     *
     * @param movie Movie to be validated
     * @return false if already exists and true if not
     */
    private boolean valid(Movie movie) {
        Optional<Movie> optional = movieRepository.findByTitle(movie.getTitle());

        if (optional.isPresent()) {
            Movie mov = optional.get();
            if (movie.getLanguage().equals(mov.getLanguage()) && movie.getMovieType().equals(mov.getMovieType())) {
                return false;
            }
        }
        return true;
    }
}