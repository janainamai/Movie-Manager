package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Payment;
import br.com.movie.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
public class PaymentService {

    private static final String PAYMENT_NOT_FOUND = "Payment not found";

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> list() {
        return paymentRepository.findAll()
                .stream()
                .sorted(comparing(Payment::getDescription))
                .collect(toList());
    }

    public List<Payment> findByDescription(String description) {
        return paymentRepository.findByDescriptionContainingIgnoreCase(description)
                .orElseThrow(() -> new BadRequestException(PAYMENT_NOT_FOUND));
    }

    public Payment save(Payment payment) {
        Optional<Payment> optional = paymentRepository.findByDescriptionIgnoreCase(payment.getDescription());

        if (optional.isPresent()) {
            throw new BadRequestException("There is already a payment with this description");
        }

        return paymentRepository.save(payment);
    }

    public Payment replace(Payment payment) {
        if (paymentRepository.existsById(payment.getId())) {
            return this.save(payment);
        } else {
            throw new BadRequestException(PAYMENT_NOT_FOUND);
        }
    }

    public void deleteById(Integer id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
        } else {
            throw new BadRequestException(PAYMENT_NOT_FOUND);
        }
    }

}