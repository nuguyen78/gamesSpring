package cz.mendelu.ea.domain.game;

import cz.mendelu.ea.domain.category.Category;
import cz.mendelu.ea.domain.category.CategoryService;
import cz.mendelu.ea.domain.genre.Genre;
import cz.mendelu.ea.domain.genre.GenreService;
import cz.mendelu.ea.domain.studio.Studio;
import cz.mendelu.ea.domain.studio.StudioService;
import cz.mendelu.ea.utils.exceptions.NotFoundException;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Optional;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class GameRequest {

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

    @NotNull
    private int studioId;

    private Set<Integer> genreIds;

    private Set<Integer> categoryIds;
   /* @NotEmpty
    private Set<String> genres;

    @NotEmpty
    private Set<String> categories;*/


    public void toGame(Game game, StudioService studioService, GenreService genreService, CategoryService categoryService) {
    game.setName(name);
    game.setReleaseDate(releaseDate);
    game.setAboutTheGame(aboutTheGame);
    game.setSupportUrl(supportUrl);
    game.setMetacriticScore(metacriticScore);
    Studio studio = studioService.getStudioById(studioId)
                    .orElseThrow(NotFoundException::new);
    game.setStudio(studio);
        game.setStudio(studio);

        if (genreIds != null) {
            Set<Genre> genres = genreIds.stream()
                    .map(genreService::findGenreById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            game.setGenres(genres);
        }

        if (categoryIds != null) {
            Set<Category> categories = categoryIds.stream()
                    .map(categoryService::findCategoryById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            game.setCategories(categories);
        }
/*    Set<Genre> genres = gameService.findGenresByIds(genreIds);
        game.setGenres(genres);*/

/*    Set<Genre> genres = gameService.findGenresByIds(this.genreIds);
        game.setGenres(genres);*/

/*        Set<Category> categories = gameService.findCategoriesByIds(this.categoryIds);
        game.setCategories(categories);*/
    }
}
