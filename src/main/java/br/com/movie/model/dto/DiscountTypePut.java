package br.com.movie.model.dto;

import br.com.movie.model.DiscountType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DiscountTypePut implements ConvertDTO<DiscountType> {

    @NotNull(message = "Identifier cannot be null")
    private Integer id;

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Percentage cannot be null")
    private Double percentage;

    @Override
    public DiscountType toEntity() {
        return DiscountType.builder()
                .id(this.id)
                .description(this.description)
                .percentage(this.percentage)
                .build();
    }
}