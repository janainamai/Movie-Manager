package br.com.movie.model.dto;

import br.com.movie.model.Language;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LanguageReplaceInput implements ConvertDTO<Language> {

    @NotNull(message = "Identifier cannot be null")
    private int id;

    @NotEmpty(message = "Description cannot be null or empty")
    private String description;

    @Override
    public Language toEntity() {
        return Language.builder()
                .id(this.id)
                .description(this.description)
                .build();
    }
}