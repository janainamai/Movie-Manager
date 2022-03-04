package br.com.movie.model.dto;

import br.com.movie.model.Language;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LanguageSaveInput implements ConvertDTO<Language> {

    @NotEmpty(message = "Description cannot be null or empty")
    private String description;

    @Override
    public Language toEntity() {
        return Language.builder()
                .description(this.description)
                .build();
    }
}