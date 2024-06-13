package cz.mendelu.ea.domain.game;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import cz.mendelu.ea.domain.genre.Genre;
import cz.mendelu.ea.domain.category.Category;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GameResponse {

    @NotNull
    private int id;

    @NotEmpty
    private String name;

    @NotEmpty
    private Date releaseDate;

    @NotEmpty
    private String aboutTheGame;

    @NotEmpty
    private String supportUrl;

    @NotEmpty
    private int metacriticScore;

    @NotEmpty
    private String studioName;

    @NotEmpty
    private Set<String> genres;

    @NotEmpty
    private Set<String> categories;

    // Constructor
    public GameResponse(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.releaseDate = game.getReleaseDate();
        this.aboutTheGame = game.getAboutTheGame();
        this.supportUrl = game.getSupportUrl();
        this.metacriticScore = game.getMetacriticScore();
        this.studioName = game.getStudio().getName();
        this.genres = game.getGenres().stream().map(Genre::getName).collect(Collectors.toSet());
        //this.genres = {'test1', 'test2', 'test3'};
        this.categories = game.getCategories().stream().map(Category::getName).collect(Collectors.toSet());
    }

}
