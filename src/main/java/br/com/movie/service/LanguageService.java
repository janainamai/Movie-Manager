package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Language;
import br.com.movie.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
public class LanguageService {

    private static final String LANGUAGE_NOT_FOUND = "Language not found";

    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> list() {
        return languageRepository.findAll()
                .stream()
                .sorted(comparing(Language::getDescription))
                .collect(toList());
    }

    public List<Language> findByDescription(String description) {
        return languageRepository.findByDescriptionContainingIgnoreCase(description)
                .orElseThrow(() -> new BadRequestException(LANGUAGE_NOT_FOUND));
    }

    public Language save(Language language) {
        Optional<Language> optional = languageRepository.findByDescriptionIgnoreCase(language.getDescription());

        if (optional.isPresent()) {
            throw new BadRequestException("There is already a language with this description");
        }

        return languageRepository.save(language);
    }

    public Language replace(Language language) {
        if (languageRepository.existsById(language.getId())) {
            return this.save(language);
        } else {
            throw new BadRequestException(LANGUAGE_NOT_FOUND);
        }
    }

    public void deleteById(Integer id) {
        if (languageRepository.existsById(id)) {
            languageRepository.deleteById(id);
        } else {
            throw new BadRequestException(LANGUAGE_NOT_FOUND);
        }
    }
}