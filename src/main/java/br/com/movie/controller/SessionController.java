package br.com.movie.controller;

import br.com.movie.model.Session;
import br.com.movie.model.Armchair;
import br.com.movie.model.dto.BookArmchairInput;
import br.com.movie.model.dto.SessionSaveInput;
import br.com.movie.model.dto.UnbookArmchairInput;
import br.com.movie.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionService service;

    @GetMapping(path = "/getArmchairsBySessionId/{sessionId}")
    public ResponseEntity<List<Armchair>> getArmchairsBySessionId(@PathVariable @NotNull Integer sessionId) {
        return ok(service.getArmchairsBySessionId(sessionId));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<List<Session>> save(@RequestBody @Valid SessionSaveInput input) {
        return ok(service.save(input));
    }

    @PostMapping(path = "/bookArmchairs")
    public ResponseEntity<List<Armchair>> bookArmchairs(@RequestBody @Valid BookArmchairInput input) {
        return ok(service.bookArmchairs(input));
    }

    @PostMapping(path = "/unbookArmchairs")
    public ResponseEntity<List<Armchair>> unbookArmchairs(@RequestBody @Valid UnbookArmchairInput input) {
        return ok(service.unbookArmchairs(input));
    }
}
