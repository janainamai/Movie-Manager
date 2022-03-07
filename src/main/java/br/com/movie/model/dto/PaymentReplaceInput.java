package br.com.movie.model.dto;

import br.com.movie.model.Payment;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PaymentReplaceInput implements ConvertDTO<Payment> {

    @NotNull(message = "Identifier cannot be null")
    private int id;

    @NotEmpty(message = "Description cannot be null or empty")
    private String description;

    @Override
    public Payment toEntity() {
        return Payment.builder()
                .id(this.id)
                .description(this.description)
                .build();
    }
}