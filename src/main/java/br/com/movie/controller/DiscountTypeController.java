package br.com.movie.controller;

import br.com.movie.model.DiscountType;
import br.com.movie.model.dto.DiscountTypePost;
import br.com.movie.model.dto.DiscountTypePut;
import br.com.movie.service.DiscountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/discountType")
public class DiscountTypeController {

    @Autowired
    private DiscountTypeService service;

    @GetMapping
    public ResponseEntity<List<DiscountType>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DiscountType> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<DiscountType> findByDescription(@RequestParam String description) {
        return ResponseEntity.ok(service.findByDescription(description));
    }

    @PostMapping
    public ResponseEntity<DiscountType> save(@RequestBody @Valid DiscountTypePost discountType) {
        return new ResponseEntity<>(service.save(discountType.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DiscountType> replace(@RequestBody @Valid DiscountTypePut discountType) {
        return ResponseEntity.ok(service.replace(discountType.toEntity()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}