package br.com.movie.model.dto;

import br.com.movie.model.Category;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryReplaceInput implements ConvertDTO<Category> {

    @NotNull(message = "Identifier cannot be null")
    private int id;

    @NotEmpty(message = "Description cannot be null or empty")
    private String description;

    @Override
    public Category toEntity() {
        return Category.builder()
                .id(this.id)
                .description(this.description)
                .build();
    }
}