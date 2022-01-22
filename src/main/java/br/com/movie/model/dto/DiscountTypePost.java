package br.com.movie.model.dto;

import br.com.movie.model.DiscountType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DiscountTypePost implements ConvertDTO<DiscountType> {

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Percentage cannot be null")
    private Double percentage;

    @Override
    public DiscountType toEntity() {
        return DiscountType.builder()
                .description(this.description)
                .percentage(this.percentage)
                .build();
    }
}