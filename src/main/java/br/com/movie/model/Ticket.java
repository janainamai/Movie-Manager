package br.com.movie.model;

import br.com.movie.model.enums.PaymentType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Poster poster;

    private Armchair armchair;

    private String cpf;

    private Double price;

    private DiscountType discountType;

    private Double discountPrice;

    private Double paymentPrice;

    private PaymentType paymentType;
}
