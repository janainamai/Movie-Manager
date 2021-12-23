package br.com.movie.model;

import br.com.movie.model.enums.PaymentType;

import javax.persistence.*;

import static javax.persistence.CascadeType.REFRESH;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = REFRESH)
    private Poster poster;

    @ManyToOne(cascade = REFRESH)
    private Armchair armchair;

    private String cpf;

    private Double price;

    @ManyToOne(cascade = REFRESH)
    private DiscountType discountType;

    private Double discountPrice;

    private Double paymentPrice;

    @ManyToOne(cascade = REFRESH)
    private PaymentType paymentType;
}
