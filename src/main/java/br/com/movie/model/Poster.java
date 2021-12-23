package br.com.movie.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

import static javax.persistence.CascadeType.REFRESH;

@Entity
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = REFRESH)
    private Movie movie;

    private LocalDate date;

    private Time hour;

    @ManyToOne(cascade = REFRESH)
    private Room room;

}
