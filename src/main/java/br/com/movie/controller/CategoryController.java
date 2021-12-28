package br.com.movie.controller;

import br.com.movie.model.dto.CategoryPost;
import br.com.movie.model.dto.CategoryPut;
import br.com.movie.model.enums.Category;
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

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        return ResponseEntity.ok(categoryService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<Category> findByDescription(@PathVariable String description) {
        return ResponseEntity.ok(categoryService.findByDescription(description));
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody @Valid CategoryPost category) {
        return new ResponseEntity<>(categoryService.save(category.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Category> replace(@RequestBody @Valid CategoryPut category) {
        return ResponseEntity.ok(categoryService.replace(category.toEntity()));
    }

}