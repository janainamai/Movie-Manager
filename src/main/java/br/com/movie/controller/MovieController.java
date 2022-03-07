package br.com.movie.controller;

import br.com.movie.model.Movie;
import br.com.movie.model.dto.MovieSaveInput;
import br.com.movie.model.dto.MovieReplaceInput;
import br.com.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Movie>> list() {
        return ok(movieService.list());
    }

    @GetMapping(path = "/findByTitleContainingIgnoreCase/{title}")
    public ResponseEntity<List<Movie>> findByTitleContainingIgnoreCase(@PathVariable String title) {
        return ok(movieService.findByTitleContainingIgnoreCase(title));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Movie> save(@RequestBody @Valid MovieSaveInput input) {
        return new ResponseEntity<>(movieService.save(input.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping(path = "/replace")
    public ResponseEntity<Movie> replace(@RequestBody @Valid MovieReplaceInput input) {
        return ok(movieService.replace(input.toEntity()));
    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        movieService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}