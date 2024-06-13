package cz.mendelu.ea.domain.genre;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenreRequest {

    @NotNull
    private int id;

    @NotEmpty
    private String name;

    public void toGenre(Genre genre) {
        //genre.setId(id);
        genre.setName(name);
    }
}