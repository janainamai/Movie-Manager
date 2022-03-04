package br.com.movie.repository;

import br.com.movie.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

    Optional<Language> findByDescriptionIgnoreCase(String description);

    Optional<List<Language>> findByDescriptionContainingIgnoreCase(String description);
}