package br.com.movie.controller;

import br.com.movie.model.Movie;
import br.com.movie.model.dto.MoviePost;
import br.com.movie.model.dto.MoviePut;
import br.com.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> list() {
        return ResponseEntity.ok(movieService.list());
    }

    @GetMapping(path = "/{title}")
    public ResponseEntity<List<Movie>> findByTitle(@PathVariable String title) {
        return ResponseEntity.ok(movieService.findByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody @Valid MoviePost movie) {
        return new ResponseEntity<>(movieService.save(movie.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Movie> replace(@RequestBody @Valid MoviePut movie) {
        return ResponseEntity.ok(movieService.replace(movie.toEntity()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}