package br.com.movie.controller;

import br.com.movie.model.Discount;
import br.com.movie.model.dto.DiscountReplaceInput;
import br.com.movie.model.dto.DiscountSaveInput;
import br.com.movie.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    @Autowired
    private DiscountService service;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Discount>> list() {
        return ok(service.list());
    }

    @GetMapping(path = "/findByDescription/{description}")
    public ResponseEntity<List<Discount>> findByDescription(@PathVariable String description) {
        return ok(service.findByDescription(description));
    }

    @GetMapping(path = "/findActiveDiscounts")
    public ResponseEntity<List<Discount>> findAtiveDiscounts() {
        return ok(service.findAtiveDiscounts());
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Discount> save(@RequestBody @Valid DiscountSaveInput input) {
        return new ResponseEntity<>(service.save(input.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping(path = "/replace")
    public ResponseEntity<Discount> replace(@RequestBody @Valid DiscountReplaceInput input) {
        return ok(service.replace(input.toEntity()));
    }

}