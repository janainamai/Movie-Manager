package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Movie;
import br.com.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
public class MovieService {

    private static final String MOVIE_NOT_FOUND = "Movie not found";

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<Movie> list() {
        return movieRepository.findAll()
                .stream()
                .sorted(comparing(Movie::getTitle))
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public Movie findById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("No movie was found with id " + id));
    }

    @Transactional(readOnly = true)
    public List<Movie> findByTitle(String title) {
        List<Movie> list = movieRepository.findByTitleContainingIgnoreCase(title);

        if (list.isEmpty()) {
            throw new BadRequestException(MOVIE_NOT_FOUND);
        } else {
            return list;
        }
    }

    @Transactional
    public Movie save(Movie movie) {
        if (!validToSave(movie)) {
            throw new BadRequestException("A movie with this name, language and type already exists");
        }
        return movieRepository.save(movie);
    }

    @Transactional
    public Movie replace(Movie movie) {
        if (validToReplace(movie)) {
            return movieRepository.save(movie);
        } else {
            throw new BadRequestException(MOVIE_NOT_FOUND);
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
        } else {
            throw new BadRequestException(MOVIE_NOT_FOUND);
        }
    }

    /**
     * Validates if a movie with this title, language and type already exists.
     */
    private boolean validToSave(Movie movie) {
        List<Movie> list = movieRepository.findByTitleIgnoreCase(movie.getTitle());

        if (list.isEmpty()) {
            return true;
        } else {
            return !hasSameLanguageAndMovieType(movie, list);
        }
    }

    /**
     * Validates if a movie with this title, language and type already exists.
     */
    private boolean validToReplace(Movie movie) {
        if (movieRepository.existsById(movie.getId())) {
            List<Movie> list = movieRepository.findByTitleIgnoreCase(movie.getTitle());

            if (list.isEmpty()) {
                return true;
            } else {
                for (Movie data : list) {
                    if (hasSameLanguageAndMovieType(movie, list) && movie.getId() != data.getId()) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasSameLanguageAndMovieType(Movie movie, List<Movie> list) {
        for (Movie mov : list) {
            if (movie.getLanguage().equals(mov.getLanguage())
                    && movie.getType().equals(mov.getType())) {
                return true;
            }
        }
        return false;
    }

}