package cz.mendelu.ea.domain.game;

import cz.mendelu.ea.domain.category.Category;
import cz.mendelu.ea.domain.genre.Genre;
import cz.mendelu.ea.domain.studio.Studio;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String name;

    @NotNull
    private Date releaseDate;

    @NotEmpty
    private String aboutTheGame;

    @NotEmpty
    private String supportUrl;

    @NotNull
    private int metacriticScore;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToMany
    @JoinTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @EqualsAndHashCode.Exclude
    Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "game_category",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @EqualsAndHashCode.Exclude
    Set<Category> categories = new HashSet<>();


    public Game(String name, Date releaseDate, String aboutTheGame, String supportUrl, int metacriticScore, Studio studio) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.aboutTheGame = aboutTheGame;
        this.supportUrl = supportUrl;
        this.metacriticScore = metacriticScore;
        this.studio = studio;
    }

}
