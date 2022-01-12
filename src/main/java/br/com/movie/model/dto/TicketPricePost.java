package br.com.movie.model.dto;

import br.com.movie.model.TicketPrice;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TicketPricePost implements ConvertDTO<TicketPrice> {

    @NotNull(message = "Price cannot be null")
    private Double price;

    @Override
    public TicketPrice toEntity() {
        return TicketPrice.builder()
                .price(this.getPrice())
                .build();
    }
}
