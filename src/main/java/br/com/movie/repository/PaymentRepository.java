package br.com.movie.repository;

import br.com.movie.model.Payment;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<List<Payment>> findByDescriptionContainingIgnoreCase(String description);

    Optional<Payment> findByDescriptionIgnoreCase(String description);

}