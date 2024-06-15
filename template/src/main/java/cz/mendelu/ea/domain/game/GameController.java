package cz.mendelu.ea.domain.game;

import cz.mendelu.ea.config.Principal;
import cz.mendelu.ea.config.PrincipalFactory;
import cz.mendelu.ea.domain.category.CategoryService;
import cz.mendelu.ea.domain.genre.GenreService;
import cz.mendelu.ea.domain.studio.StudioService;
import cz.mendelu.ea.utils.response.ArrayResponse;
import cz.mendelu.ea.utils.response.ObjectResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("games")
@Validated
public class GameController {

    private final GameService gameService;
    private final StudioService studioService;
    private final GenreService genreService;
    private final CategoryService categoryService;

    private PrincipalFactory principalFactory;

    @Autowired
    public GameController(GameService gameService, StudioService studioService, GenreService genreService, CategoryService categoryService, PrincipalFactory principalFactory) {
        this.gameService = gameService;
        this.studioService = studioService;
        this.genreService = genreService;
        this.categoryService = categoryService;
        this.principalFactory = principalFactory;
    }

/*    @GetMapping(value = "", produces = "application/json")
    @Valid
    public ArrayResponse<GameResponse> getGames() {
        return ArrayResponse.of(
                gameService.getAllGames(),
                GameResponse::new
        );
    }*/

    @GetMapping(value = "", produces = "application/json")
    public ArrayResponse<GameResponse> getGames(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Game> movies = gameService.getAllGames(page, size);
        return ArrayResponse.of(movies, GameResponse::new);
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    @Valid
    public ObjectResponse<GameResponse> getGameById(@PathVariable Integer id) {
        Game game = gameService
                .findGameById(id);
        return ObjectResponse.of(game, GameResponse::new);
    }

    @PostMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Valid
    public ObjectResponse<GameResponse> createGame(@RequestBody @Valid GameRequest gameRequest, @AuthenticationPrincipal Jwt jwt) {
        Principal principal = principalFactory.of(jwt);
        System.out.println(principal);
        Game game = new Game();
        gameRequest.toGame(game ,studioService, genreService, categoryService);
        game = gameService.createGame(game);
        return ObjectResponse.of(game, GameResponse::new);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    @Valid
    public ObjectResponse<GameResponse> updateGame(@PathVariable Integer id, @RequestBody @Valid GameRequest gameRequest) {
        Game game = gameService.findGameById(id);
        gameRequest.toGame(game ,studioService, genreService, categoryService);
        Game updatedGame = gameService.updateGame(game);
        return ObjectResponse.of(updatedGame, GameResponse::new);
    }


    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> deleteGame(@PathVariable Integer id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok("Game was successfully deleted");
    }

    @GetMapping(value = "/top-rated-by-genre/{genreId}", produces = "application/json")
    @Valid
    public ArrayResponse<GameResponse> getTopRatedGamesByGenre(@PathVariable Integer genreId) {
        return ArrayResponse.of(
                gameService.getTopRatedGamesByGenre(genreId),
                GameResponse::new
        );
    }

    @GetMapping(value = "/most-reviewed-per-category", produces = "application/json")
    @Valid
    public ObjectResponse<Map<String, GameResponse>> getMostReviewedGameByCategory() {
        Map<String, Game> mostReviewedGamesByCategory = gameService.getMostReviewedGameByCategory();
        return ObjectResponse.of(
                mostReviewedGamesByCategory,
                gamesMap -> gamesMap.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> new GameResponse(entry.getValue())
                        ))
        );
    }

    @GetMapping(value = "/get-games-by-release-date-between/{minYear}/{maxYear}", produces = "application/json")
    @Valid
    public ArrayResponse<GameResponse> getGamesByReleaseYearBetween(@PathVariable int minYear, @PathVariable int maxYear) {
        List<Game> games = gameService.getGamesByReleaseYearBetween(minYear, maxYear);
        return ArrayResponse.of(games, GameResponse::new);
    }

    @GetMapping(value = "/oldest-studio-game", produces = "application/json")
    public ObjectResponse<GameResponse> getGameWithOldestStudio() {
        Game game = gameService.getGameWithOldestStudio();
        return ObjectResponse.of(game, GameResponse::new);
    }

}
