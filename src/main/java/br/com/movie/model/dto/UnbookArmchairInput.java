package br.com.movie.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UnbookArmchairInput {

    @NotNull(message = "sessionId cannot be null")
    private Integer sessionId;

    @NotEmpty(message = "at least one armchair id must be informed")
    private List<Integer> armchairsId;

}
