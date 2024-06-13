package cz.mendelu.ea.domain.game;

import cz.mendelu.ea.config.Principal;
import cz.mendelu.ea.config.PrincipalFactory;
import cz.mendelu.ea.domain.category.CategoryService;
import cz.mendelu.ea.domain.genre.GenreService;
import cz.mendelu.ea.domain.studio.StudioService;
import cz.mendelu.ea.utils.exceptions.NotFoundException;
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

    @GetMapping(value = "", produces = "application/json")
    @Valid
    public ArrayResponse<GameResponse> getGames() {
        return ArrayResponse.of(
                gameService.getAllGames(),
                GameResponse::new
        );
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
/*
    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer id) {
        gameService.deleteGame(id);
    }*/
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> deleteGame(@PathVariable Integer id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok("Game was successfully deleted");
    }

}
