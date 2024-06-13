package cz.mendelu.ea.domain.studio;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class StudioResponse {

    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Date foundedDate;

    @NotNull
    private int numberOfWorkers;

    public StudioResponse(Studio studio) {
        this.id = studio.getId();
        this.name = studio.getName();
        this.foundedDate = studio.getFoundedDate();
        this.numberOfWorkers = studio.getNumberOfWorkers();
    }
}
