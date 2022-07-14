package br.com.movie.controller;

import br.com.movie.model.Category;
import br.com.movie.model.dto.CategoryReplaceInput;
import br.com.movie.model.dto.CategorySaveInput;
import br.com.movie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Category>> list() {
        return ResponseEntity.ok(categoryService.list());
    }

    @GetMapping("/findByDescription/{description}")
    public ResponseEntity<List<Category>> findByDescription(@PathVariable String description) {
        return ResponseEntity.ok(categoryService.findByDescription(description));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Category> save(@RequestBody @Valid CategorySaveInput input) {
        return new ResponseEntity<>(categoryService.save(input.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping(path = "/replace")
    public ResponseEntity<Category> replace(@RequestBody @Valid CategoryReplaceInput input) {
        return ResponseEntity.ok(categoryService.replace(input.toEntity()));
    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}