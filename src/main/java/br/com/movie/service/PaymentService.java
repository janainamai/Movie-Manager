package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.enums.Payment;
import br.com.movie.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private static final String PAYMENT_NOT_FOUND = "Payment not found";

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> list() {
        return paymentRepository.findAll();
    }

    public Payment findById(Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(PAYMENT_NOT_FOUND));
    }

    public Payment findByDescription(String description) {
        return paymentRepository.findByDescription(description.toUpperCase())
                .orElseThrow(() -> new BadRequestException(PAYMENT_NOT_FOUND));
    }

    public Payment save(Payment payment) {
        Optional<Payment> optional = paymentRepository.findByDescription(payment.getDescription().toUpperCase());

        if (optional.isPresent()) {
            throw new BadRequestException("There is already a payment with this description");
        }

        payment.setDescription(payment.getDescription().toUpperCase());
        return paymentRepository.save(payment);
    }

    public Payment replace(Payment payment) {
        if (paymentRepository.existsById(payment.getId())) {
            payment.setDescription(payment.getDescription().toUpperCase());
            return this.save(payment);
        } else {
            throw new BadRequestException(PAYMENT_NOT_FOUND);
        }
    }

    public void delete(Integer id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
        } else {
            throw new BadRequestException(PAYMENT_NOT_FOUND);
        }
    }

}