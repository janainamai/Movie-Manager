package br.com.movie.model.dto;

import br.com.movie.model.ArmchairModel;
import lombok.Data;

import java.util.List;

@Data
public class RoomSaveOutput {

    private String name;

    private List<ArmchairModel> armchairs;
}
