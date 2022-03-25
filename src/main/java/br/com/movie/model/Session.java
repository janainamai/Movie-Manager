package br.com.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.REFRESH;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = REFRESH)
    private Movie movie;

    private LocalDateTime dateTime;

    @ManyToOne(cascade = REFRESH)
    private Room room;

}
