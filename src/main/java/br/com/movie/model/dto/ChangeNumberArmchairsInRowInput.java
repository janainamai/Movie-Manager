package br.com.movie.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ChangeNumberArmchairsInRowInput {

    @NotNull(message = "roomId cannot be null")
    Integer roomId;

    @NotEmpty(message = "letterRow cannot be null or empty")
    String letterRow;

    @NotNull(message = "numberOfArmairs cannot be null")
    Integer numberOfArmchairs;

}
