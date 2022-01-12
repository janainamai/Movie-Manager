package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Language;
import br.com.movie.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    private static final String LANGUAGE_NOT_FOUND = "Language not found";

    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> list() {
        return languageRepository.findAll();
    }

    public Language findById(Integer id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(LANGUAGE_NOT_FOUND));
    }

    public Language findByDescription(String description) {
        return languageRepository.findByDescription(description.toUpperCase())
                .orElseThrow(() -> new BadRequestException(LANGUAGE_NOT_FOUND));
    }

    public Language save(Language language) {
        Optional<Language> optional = languageRepository.findByDescription(language.getDescription().toUpperCase());

        if (optional.isPresent()) {
            throw new BadRequestException("There is already a language with this description");
        }

        language.setDescription(language.getDescription().toUpperCase());
        return languageRepository.save(language);
    }

    public Language replace(Language language) {
        if (languageRepository.existsById(language.getId())) {
            language.setDescription(language.getDescription().toUpperCase());
            return this.save(language);
        } else {
            throw new BadRequestException(LANGUAGE_NOT_FOUND);
        }
    }

    public void delete(Integer id) {
        if (languageRepository.existsById(id)) {
            languageRepository.deleteById(id);
        } else {
            throw new BadRequestException(LANGUAGE_NOT_FOUND);
        }
    }
}