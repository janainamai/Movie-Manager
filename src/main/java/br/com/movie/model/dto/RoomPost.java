package br.com.movie.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoomPost {

    @NotNull( message = "name cannot be null")
    private String name;

    @NotNull(message = "numberOfRows cannot be null")
    private Integer numberOfRows;

    @NotNull(message = "armchairPerRow cannot be null")
    private Integer armchairPerRow;
}
