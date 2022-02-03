package br.com.movie.repository;

import br.com.movie.model.Armchair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmchairRepository extends JpaRepository<Armchair, Integer> {
}
