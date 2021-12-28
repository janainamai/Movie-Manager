package br.com.movie.controller;

import br.com.movie.model.dto.PaymentPost;
import br.com.movie.model.dto.PaymentPut;
import br.com.movie.model.enums.Payment;
import br.com.movie.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> list() {
        return ResponseEntity.ok(paymentService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<Payment> findByDescription(@PathVariable String description) {
        return ResponseEntity.ok(paymentService.findByDescription(description));
    }

    @PostMapping
    public ResponseEntity<Payment> save(@RequestBody @Valid PaymentPost payment) {
        return new ResponseEntity<>(paymentService.save(payment.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Payment> replace(@RequestBody @Valid PaymentPut payment) {
        return ResponseEntity.ok(paymentService.replace(payment.toEntity()));
    }

}