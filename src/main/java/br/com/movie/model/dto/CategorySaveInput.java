package br.com.movie.model.dto;

import br.com.movie.model.Category;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategorySaveInput implements ConvertDTO<Category> {

    @NotEmpty(message = "Description cannot be null or empty")
    private String description;

    @Override
    public Category toEntity() {
        return Category.builder()
                .description(this.description)
                .build();
    }
}