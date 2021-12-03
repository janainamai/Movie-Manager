package br.com.movie.model;

import br.com.movie.model.enums.Category;
import br.com.movie.model.enums.Language;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String synopsis;

    private Category category;

    private int ageGroup;

    private Language language;

    private int duration;

}
