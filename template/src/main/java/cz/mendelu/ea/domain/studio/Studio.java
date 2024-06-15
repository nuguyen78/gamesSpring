package cz.mendelu.ea.domain.studio;

import cz.mendelu.ea.domain.game.Game;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"studio\"")
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private Date foundedDate;

    @NotNull
    private int numberOfWorkers;

    public Studio(String name, Date foundedDate, int numberOfWorkers) {
        this.name = name;
        this.foundedDate = foundedDate;
        this.numberOfWorkers = numberOfWorkers;
    }
}