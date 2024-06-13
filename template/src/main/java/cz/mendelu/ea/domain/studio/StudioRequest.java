package cz.mendelu.ea.domain.studio;

import cz.mendelu.ea.domain.game.GameService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class StudioRequest {

    @NotNull
    private String name;

    @NotNull
    private Date foundedDate;

    @NotNull
    private int numberOfWorkers;


    public void toStudio(Studio studio) {
        studio.setName(name);
        studio.setFoundedDate(foundedDate);
        studio.setNumberOfWorkers(numberOfWorkers);
    }

}