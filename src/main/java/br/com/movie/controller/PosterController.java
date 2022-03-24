package br.com.movie.controller;

import br.com.movie.model.Poster;
import br.com.movie.model.PosterArmchair;
import br.com.movie.model.dto.BookArmchairInput;
import br.com.movie.model.dto.PosterSaveInput;
import br.com.movie.model.dto.UnbookArmchairInput;
import br.com.movie.service.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/poster")
public class PosterController {

    @Autowired
    private PosterService service;

    @GetMapping(path = "/getArmchairsByPosterId/{posterId}")
    public ResponseEntity<List<PosterArmchair>> getArmchairsByPosterId(@PathVariable @NotNull Integer posterId) {
        return ok(service.getArmchairsByPosterId(posterId));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<List<Poster>> save(@RequestBody @Valid PosterSaveInput input) {
        return ok(service.save(input));
    }

    @PostMapping(path = "/bookArmchairs")
    public ResponseEntity<List<PosterArmchair>> bookArmchairs(@RequestBody @Valid BookArmchairInput input) {
        return ok(service.bookArmchairs(input));
    }

    @PostMapping(path = "/unbookArmchairs")
    public ResponseEntity<List<PosterArmchair>> unbookArmchairs(@RequestBody @Valid UnbookArmchairInput input) {
        return ok(service.unbookArmchairs(input));
    }
}
