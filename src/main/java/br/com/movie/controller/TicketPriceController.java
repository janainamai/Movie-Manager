package br.com.movie.controller;

import br.com.movie.model.TicketPrice;
import br.com.movie.model.dto.TicketPriceSaveInput;
import br.com.movie.service.TicketPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/ticketprice")
public class TicketPriceController {

    @Autowired
    private TicketPriceService ticketPriceService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<TicketPrice>> list() {
        return ok(ticketPriceService.list());
    }

    @GetMapping(path = "/getCurrentPrice")
    public ResponseEntity<Double> getCurrentPrice() {
        return ok(ticketPriceService.getCurrentPrice());
    }

    @PostMapping(path = "/save")
    public ResponseEntity<TicketPrice> save(@RequestBody @Valid TicketPriceSaveInput input) {
        return new ResponseEntity<>(ticketPriceService.saveNewTicketPrice(input.toEntity()), HttpStatus.CREATED);
    }
}
