package br.com.movie.model;

import br.com.movie.model.enums.MovieType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.REFRESH;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String synopsis;

    @ManyToMany(cascade = REFRESH)
    private List<Category> category;

    private int ageGroup;

    @ManyToOne(cascade = REFRESH)
    private Language language;

    private int duration;

    private MovieType movieType;

}