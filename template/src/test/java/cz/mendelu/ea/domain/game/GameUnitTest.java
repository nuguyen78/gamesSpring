package cz.mendelu.ea.domain.game;

import cz.mendelu.ea.domain.category.Category;
import cz.mendelu.ea.domain.category.CategoryRepository;
import cz.mendelu.ea.domain.category.CategoryService;
import cz.mendelu.ea.domain.review.Review;
import cz.mendelu.ea.domain.review.ReviewRepository;
import cz.mendelu.ea.domain.studio.Studio;
import cz.mendelu.ea.utils.response.ObjectResponse;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import cz.mendelu.ea.utils.response.ArrayResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)

public class GameUnitTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    private CategoryService categoryService;

    private Game game1;
    private Game game2;
    private Category category;
    private List<Review> reviews;

    @BeforeEach
    void setUp() {
        Studio studio1 = new Studio("Studio One", new Date(2021, 9, 10), 150);
        Studio studio2 = new Studio("Studio Two", new Date(2021, 9, 10), 80);
        game1 = new Game("Game One", new Date(2021, 9, 10), "An exciting adventure game.", "http://support.gameone.com", 85, studio1);
        game2 = new Game("Game Two", new Date(2021, 9, 10), "A thrilling action game.", "http://support.gametwo.com", 22, studio2);

        category = new Category();
        category.setName("Action");
        category.setGames(new HashSet<>(Arrays.asList(game1, game2)));

        // Create reviews
        reviews = Arrays.asList(
                new Review("Review 1 for Game 1", new Date(2021, 9, 11), game1),
                new Review("Review 2 for Game 1", new Date(2021, 9, 12), game1),
                new Review("Review 1 for Game 2", new Date(2021, 9, 13), game2),
                new Review("Review 2 for Game 2", new Date(2021, 9, 14), game2),
                new Review("Review 3 for Game 2", new Date(2021, 9, 15), game2)
        );


    }

    @Test
    void testGetGames() {
        List<Game> games = Arrays.asList(game1, game2);
        when(gameService.getAllGames(0,10)).thenReturn(games);

        ArrayResponse<GameResponse> response = gameController.getGames(0, 10);

        verify(gameService).getAllGames(0, 10);
        assertThat(response.getItems().size(), is(2));
        assertThat(response.getItems().get(0).getName(), is("Game One"));
        assertThat(response.getItems().get(1).getName(), is("Game Two"));
    }

    @Test
    void testGetGameById() {
        when(gameService.findGameById(1)).thenReturn(game1);

        ObjectResponse<GameResponse> response = gameController.getGameById(1);

        verify(gameService).findGameById(1);
        assertThat(response.getContent().getName(), is("Game One"));
        assertThat(response.getContent().getMetacriticScore(), is(85));
    }

    @Test
    void testGetGamesByReleaseYearBetween() {
        int minYear = 2020;
        int maxYear = 2025;

        List<Game> filteredGames = Arrays.asList(game1, game2);

        when(gameService.getGamesByReleaseYearBetween(minYear, maxYear)).thenReturn(filteredGames);

        List<Game> result = gameService.getGamesByReleaseYearBetween(minYear, maxYear);

        verify(gameService).getGamesByReleaseYearBetween(minYear, maxYear);
        assertThat(result.size(), is(2));
        assertThat(result.get(0).getName(), is("Game One"));
        assertThat(result.get(1).getName(), is("Game Two"));
    }

    @Test
    void testGetMostReviewedGameByCategory() {
        List<Category> categories = Arrays.asList(category);

        // Emulate the getMostReviewedGameByCategory logic
        Map<String, Game> mostReviewedGamesByCategory = new HashMap<>();
        for (Category category : categories) {
            Game mostReviewedGame = category.getGames().stream()
                    .max(Comparator.comparingInt(game -> (int) reviews.stream().filter(review -> review.getGame().equals(game)).count()))
                    .orElse(null);
            if (mostReviewedGame != null) {
                mostReviewedGamesByCategory.put(category.getName(), mostReviewedGame);
            }
        }

        assertThat(mostReviewedGamesByCategory, is(notNullValue()));
        assertThat(mostReviewedGamesByCategory.size(), is(1));
        assertThat(mostReviewedGamesByCategory.get("Action"), is(game2));
    }

    @Test
    void testGetGameWithOldestStudio() {
        when(gameService.getGameWithOldestStudio()).thenReturn(game1);

        ObjectResponse<GameResponse> response = gameController.getGameWithOldestStudio();

        verify(gameService).getGameWithOldestStudio();
        assertThat(response.getContent().getName(), is("Game One"));
    }

    @Test
    void testGetTopRatedGamesByGenre() {
        int genreId = 1;
        List<Game> topRatedGames = Arrays.asList(game1, game2);

        when(gameService.getTopRatedGamesByGenre(genreId)).thenReturn(topRatedGames);

        ArrayResponse<GameResponse> response = gameController.getTopRatedGamesByGenre(genreId);

        verify(gameService).getTopRatedGamesByGenre(genreId);
        assertThat(response.getItems().size(), is(2));
        assertThat(response.getItems().get(0).getName(), is("Game One"));
        assertThat(response.getItems().get(1).getName(), is("Game Two"));
    }
}
