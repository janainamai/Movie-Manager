package br.com.movie.repository;

import br.com.movie.model.DayOfWeekDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DayOfWeekDiscountRepository extends JpaRepository<DayOfWeekDiscount, Integer> {

    Optional<DayOfWeekDiscount> findByDayOfWeekIgnoreCase(String dayOfWeek);

}