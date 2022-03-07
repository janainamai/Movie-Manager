package br.com.movie.controller;

import br.com.movie.model.DayOfWeekDiscount;
import br.com.movie.model.dto.DayOfWeekDiscountReplaceInput;
import br.com.movie.service.DayOfWeekDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dayofweekdiscount")
public class DayOfWeekDiscountController {

    @Autowired
    private DayOfWeekDiscountService service;

    @GetMapping(path = "/list")
    public ResponseEntity<List<DayOfWeekDiscount>> list() {
        return ResponseEntity.ok(service.list());
    }

    @PutMapping(path = "/replace")
    public ResponseEntity<DayOfWeekDiscount> replace(@RequestBody @Valid DayOfWeekDiscountReplaceInput input) {
        return ResponseEntity.ok(service.replace(input.toEntity()));
    }

}
