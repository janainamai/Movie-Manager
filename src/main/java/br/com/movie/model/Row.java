package br.com.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Row implements Comparable<Row> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String letter;

    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Armchair> armchairs;

    @Override
    public int compareTo(Row row) {
        return this.getLetter().compareToIgnoreCase(row.getLetter());
    }
}