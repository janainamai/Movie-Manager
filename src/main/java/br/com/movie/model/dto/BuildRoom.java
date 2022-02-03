package br.com.movie.model.dto;

import br.com.movie.model.Room;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BuildRoom {

    @NotNull( message = "Room cannot be null")
    private Room room;

    @NotNull(message = "numberOfRows cannot be null")
    private Integer numberOfRows;

    @NotNull(message = "armchairPerRow cannot be null")
    private Integer armchairPerRow;
}
