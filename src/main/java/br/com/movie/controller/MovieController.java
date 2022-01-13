package br.com.movie.controller;

import br.com.movie.model.Movie;
import br.com.movie.model.dto.MoviePost;
import br.com.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody @Valid MoviePost movie) {
        return ResponseEntity.ok(movieService.save(movie.toEntity()));
    }

}