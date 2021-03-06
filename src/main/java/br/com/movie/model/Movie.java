package br.com.movie.model;

import br.com.movie.model.enums.MovieType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.REFRESH;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String synopsis;

    @ManyToMany(cascade = REFRESH)
    private List<Category> category;

    private Integer minimumAgeRequired;

    @ManyToOne(cascade = REFRESH)
    private Language language;

    private Integer durationMinutes;

    private MovieType type;

}