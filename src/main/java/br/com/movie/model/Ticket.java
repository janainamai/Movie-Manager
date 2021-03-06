package br.com.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.CascadeType.REFRESH;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = REFRESH)
    private Session session;

    @ManyToOne(cascade = REFRESH)
    private ArmchairModel armchair;

    private String cpf;

    private Double price;

    @ManyToOne(cascade = REFRESH)
    private Discount discount;

    private Double discountPrice;

    private Double paymentPrice;

    @ManyToOne(cascade = REFRESH)
    private Payment paymentType;
}
