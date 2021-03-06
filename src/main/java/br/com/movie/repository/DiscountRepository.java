package br.com.movie.repository;

import br.com.movie.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    Optional<List<Discount>> findByDescriptionContainingIgnoreCase(String description);

    Optional<Discount> findByDescriptionIgnoreCase(String description);

    Optional<List<Discount>> findByActiveTrue();
}