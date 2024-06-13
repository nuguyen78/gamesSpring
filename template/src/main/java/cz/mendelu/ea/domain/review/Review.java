package cz.mendelu.ea.domain.review;

import cz.mendelu.ea.domain.game.Game;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"review\"")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String text;

    @NotNull
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

}
