package br.com.movie.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.REFRESH;

@Entity
public class Armchair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String letter;

    private int number;

    private boolean vip;

    @OneToOne(cascade = REFRESH)
    private Room room;
}
