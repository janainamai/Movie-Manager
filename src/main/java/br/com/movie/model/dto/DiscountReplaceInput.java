package br.com.movie.model.dto;

import br.com.movie.model.Discount;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DiscountReplaceInput implements ConvertDTO<Discount> {

    @NotNull(message = "identifier cannot be null")
    private Integer id;

    @NotNull(message = "description cannot be null")
    @NotEmpty(message = "description cannot be empty")
    private String description;

    @NotNull(message = "percentage cannot be null")
    private Double percentage;

    @NotNull(message = "active cannot be null")
    private boolean active;

    @Override
    public Discount toEntity() {
        return Discount.builder()
                .id(this.id)
                .description(this.description)
                .percentage(this.percentage)
                .active(this.active)
                .build();
    }
}