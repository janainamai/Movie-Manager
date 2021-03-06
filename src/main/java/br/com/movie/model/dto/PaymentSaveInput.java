package br.com.movie.model.dto;

import br.com.movie.model.Payment;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PaymentSaveInput implements ConvertDTO<Payment> {

    @NotEmpty(message = "Description cannot be null or empty")
    private String description;

    @Override
    public Payment toEntity() {
        return Payment.builder()
                .description(this.description)
                .build();
    }
}