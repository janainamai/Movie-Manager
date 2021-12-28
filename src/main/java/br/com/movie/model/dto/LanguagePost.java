package br.com.movie.model.dto;

import br.com.movie.model.enums.Language;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LanguagePost implements ConvertDTO<Language> {

    @NotEmpty(message = "Description cannot be null or empty")
    private String description;

    @Override
    public Language toEntity() {
        return Language.builder()
                .description(this.description)
                .build();
    }
}