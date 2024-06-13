package cz.mendelu.ea.domain.genre;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GenreResponse {

    @NotNull
    private int id;

    @NotEmpty
    private String name;

    public GenreResponse(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }
}
