package br.com.movie.controller;

import br.com.movie.model.TicketPrice;
import br.com.movie.model.dto.TicketPricePost;
import br.com.movie.service.TicketPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ticketprice")
public class TicketPriceController {

    @Autowired
    private TicketPriceService ticketPriceService;

    @GetMapping
    public ResponseEntity<Double> getCurrentPrice() {
        return ResponseEntity.ok(ticketPriceService.getCurrentPrice());
    }

    @PostMapping
    public ResponseEntity<TicketPrice> save(@RequestBody @Valid TicketPricePost ticketPrice) {
        return new ResponseEntity<>(ticketPriceService.saveNewTicketPrice(ticketPrice.toEntity()), HttpStatus.CREATED);
    }
}
