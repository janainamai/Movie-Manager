package br.com.movie.repository;

import br.com.movie.model.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountTypeRepository extends JpaRepository<DiscountType, Integer> {

    Optional<DiscountType> findByDescriptionIgnoreCase(String description);
}