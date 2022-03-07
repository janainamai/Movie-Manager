package br.com.movie.model.dto;

import br.com.movie.model.DayOfWeekDiscount;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DayOfWeekDiscountPut implements ConvertDTO<DayOfWeekDiscount> {

    @NotNull(message = "id cannot be null")
    private Integer id;

    @NotNull(message = "dayOfWeek cannot be null")
    @NotEmpty(message = "dayOfWeek cannot be empty")
    private String dayOfWeek;

    @NotNull(message = "isActive cannot be null")
    private Boolean active;

    @NotNull(message = "percentage cannot be null")
    private Double percentage;

    @Override
    public DayOfWeekDiscount toEntity() {
        return DayOfWeekDiscount.builder()
                .id(id)
                .dayOfWeek(dayOfWeek)
                .active(active)
                .percentage(percentage)
                .build();
    }
}