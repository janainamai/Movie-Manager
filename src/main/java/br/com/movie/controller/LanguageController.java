package br.com.movie.controller;

import br.com.movie.model.Language;
import br.com.movie.model.dto.LanguageReplaceInput;
import br.com.movie.model.dto.LanguageSaveInput;
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

    @GetMapping(path = "/list")
    public ResponseEntity<List<Language>> list() {
        return ResponseEntity.ok(languageService.list());
    }

    @GetMapping("/findByDescription/{description}")
    public ResponseEntity<List<Language>> findByDescription(@PathVariable String description) {
        return ResponseEntity.ok(languageService.findByDescription(description));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Language> save(@RequestBody @Valid LanguageSaveInput input) {
        return new ResponseEntity<>(languageService.save(input.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping(path = "/replace")
    public ResponseEntity<Language> replace(@RequestBody @Valid LanguageReplaceInput language) {
        return ResponseEntity.ok(languageService.replace(language.toEntity()));
    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        languageService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}