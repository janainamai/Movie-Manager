package br.com.movie.controller;

import br.com.movie.model.Discount;
import br.com.movie.model.dto.DiscountPost;
import br.com.movie.model.dto.DiscountPut;
import br.com.movie.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/discountType")
public class DiscountController {

    @Autowired
    private DiscountService service;

    @GetMapping
    public ResponseEntity<List<Discount>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Discount> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Discount> findByDescription(@RequestParam String description) {
        return ResponseEntity.ok(service.findByDescription(description));
    }

    @PostMapping
    public ResponseEntity<Discount> save(@RequestBody @Valid DiscountPost discount) {
        return new ResponseEntity<>(service.save(discount.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Discount> replace(@RequestBody @Valid DiscountPut discount) {
        return ResponseEntity.ok(service.replace(discount.toEntity()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}