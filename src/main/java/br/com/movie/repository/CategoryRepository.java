package br.com.movie.repository;

import br.com.movie.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByDescriptionIgnoreCase(String description);

    Optional<List<Category>> findByDescriptionContainingIgnoreCase(String description);
}