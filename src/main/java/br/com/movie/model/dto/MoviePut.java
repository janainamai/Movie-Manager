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
public class MoviePut implements ConvertDTO<Movie>{

    @NotNull(message = "Identifier cannot be null")
    private Integer id;

    @NotEmpty(message = "Title cannot be null")
    private String title;

    @NotEmpty(message = "Synopsis cannot be null")
    private String synopsis;

    @NotEmpty(message = "Category cannot be null")
    private List<Category> category;

    @NotNull(message = "Age group cannot be null")
    private int ageGroup;

    @NotNull(message = "Language cannot be null")
    private Language language;

    @NotNull(message = "Duration cannot be null")
    private int duration;

    @NotNull(message = "Movie type cannot be null")
    private MovieType movieType;

    @Override
    public Movie toEntity() {
        return Movie.builder()
                .id(this.id)
                .title(this.title)
                .synopsis(this.synopsis)
                .category(this.category)
                .ageGroup(this.ageGroup)
                .language(this.language)
                .duration(this.duration)
                .movieType(this.movieType)
                .build();
    }
}