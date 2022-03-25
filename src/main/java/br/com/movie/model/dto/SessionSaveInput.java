package br.com.movie.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SessionSaveInput {

    @NotNull(message = "movieId cannot be null")
    private Integer movieId;

    @NotEmpty(message = "at least one day must be informed")
    private List<LocalDateTime> days;

    @NotEmpty(message = "at least one time must be informed")
    private List<LocalDateTime> hours;

    @NotNull(message = "roomId cannot be null")
    private Integer roomId;
}
