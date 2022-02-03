package br.com.movie.model.dto;

import br.com.movie.model.Room;
import lombok.Data;

@Data
public class BuildRoom {

    private Room room;

    private Integer numberOfRows;

    private Integer armchairPerRow;
}
