package br.com.movie.controller;

import br.com.movie.model.dto.LanguagePost;
import br.com.movie.model.dto.LanguagePut;
import br.com.movie.model.enums.Language;
import br.com.movie.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<Language>> list() {
        return ResponseEntity.ok(languageService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(languageService.findById(id));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<Language> findByDescription(@PathVariable String description) {
        return ResponseEntity.ok(languageService.findByDescription(description));
    }

    @PostMapping
    public ResponseEntity<Language> save(@RequestBody @Valid LanguagePost language) {
        return new ResponseEntity<>(languageService.save(language.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Language> replace(@RequestBody @Valid LanguagePut language) {
        return ResponseEntity.ok(languageService.replace(language.toEntity()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        languageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}