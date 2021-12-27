package br.com.movie.model.dto;

import br.com.movie.model.Room;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RoomPost implements ConvertDTO<Room> {

    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @Override
    public Room toEntity() {
        return Room.builder()
                .name(this.name)
                .build();
    }
}