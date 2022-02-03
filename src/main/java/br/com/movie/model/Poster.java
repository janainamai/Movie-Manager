package br.com.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.REFRESH;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany
    private List<Armchair> armchairs;
}
