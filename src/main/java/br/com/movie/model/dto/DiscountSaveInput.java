package br.com.movie.model.dto;

import br.com.movie.model.Discount;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DiscountSaveInput implements ConvertDTO<Discount> {

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Percentage cannot be null")
    private Double percentage;

    @Override
    public Discount toEntity() {
        return Discount.builder()
                .description(this.description)
                .percentage(this.percentage)
                .build();
    }
}