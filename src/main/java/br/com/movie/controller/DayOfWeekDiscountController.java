package br.com.movie.controller;

import br.com.movie.model.DayOfWeekDiscount;
import br.com.movie.model.dto.DayOfWeekDiscountPost;
import br.com.movie.model.dto.DayOfWeekDiscountPut;
import br.com.movie.service.DayOfWeekDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dayofweekdiscount")
public class DayOfWeekDiscountController {

    @Autowired
    private DayOfWeekDiscountService service;

    @GetMapping
    public ResponseEntity<List<DayOfWeekDiscount>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DayOfWeekDiscount> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/find")
    public ResponseEntity<DayOfWeekDiscount> findByDayOfWeek(@RequestParam String dayOfWeek) {
        return ResponseEntity.ok(service.findByDayOfWeek(dayOfWeek));
    }

    @PostMapping
    public ResponseEntity<DayOfWeekDiscount> save(@RequestBody @Valid DayOfWeekDiscountPost dayOfWeekDiscount) {
        return new ResponseEntity<>(service.save(dayOfWeekDiscount.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DayOfWeekDiscount> replace(@RequestBody @Valid DayOfWeekDiscountPut dayOfWeekDiscount) {
        return ResponseEntity.ok(service.replace(dayOfWeekDiscount.toEntity()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
