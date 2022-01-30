package br.com.movie.model.dto;

import br.com.movie.model.Category;
import br.com.movie.model.Language;
import br.com.movie.model.Movie;
import br.com.movie.model.enums.MovieType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MoviePost implements ConvertDTO<Movie>{

    @NotEmpty(message = "Title cannot be null")
    private String title;

    @NotEmpty(message = "Synopsis cannot be null")
    private String synopsis;

    @NotEmpty(message = "Category cannot be null")
    private List<Category> category;

    @NotNull(message = "MinimumAgeRequired cannot be null")
    private Integer minimumAgeRequired;

    @NotNull(message = "Language cannot be null")
    private Language language;

    @NotNull(message = "DurationMinutes cannot be null")
    private Integer durationMinutes;

    @NotNull(message = "Type cannot be null")
    private MovieType type;

    @Override
    public Movie toEntity() {
        return Movie.builder()
                .title(this.title)
                .synopsis(this.synopsis)
                .category(this.category)
                .minimumAgeRequired(this.minimumAgeRequired)
                .language(this.language)
                .durationMinutes(this.durationMinutes)
                .type(this.type)
                .build();
    }
}