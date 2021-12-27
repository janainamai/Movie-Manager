package br.com.movie.model.dto;

import br.com.movie.model.Room;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RoomPut implements ConvertDTO<Room> {

    @NotNull(message = "Identifier cannot be null")
    private int id;

    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @Override
    public Room toEntity() {
        return Room.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}