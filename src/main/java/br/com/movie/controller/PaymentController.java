package br.com.movie.controller;

import br.com.movie.model.Payment;
import br.com.movie.model.dto.PaymentReplaceInput;
import br.com.movie.model.dto.PaymentSaveInput;
import br.com.movie.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Payment>> list() {
        return ok(paymentService.list());
    }

    @GetMapping("/findByDescription/{description}")
    public ResponseEntity<List<Payment>> findByDescription(@PathVariable String description) {
        return ok(paymentService.findByDescription(description));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Payment> save(@RequestBody @Valid PaymentSaveInput input) {
        return new ResponseEntity<>(paymentService.save(input.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping(path = "/replace")
    public ResponseEntity<Payment> replace(@RequestBody @Valid PaymentReplaceInput input) {
        return ok(paymentService.replace(input.toEntity()));
    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        paymentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}