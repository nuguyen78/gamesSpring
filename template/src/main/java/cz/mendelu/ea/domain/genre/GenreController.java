package cz.mendelu.ea.domain.genre;

import cz.mendelu.ea.domain.game.GameService;
import cz.mendelu.ea.utils.response.ArrayResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("genres")
@Validated
public class GenreController {

    private final GenreService genreService;

    private final GameService gameService;

    @Autowired
    public GenreController(GenreService genreService, GameService gameService) {
        this.genreService = genreService;
        this.gameService = gameService;
    }

    @GetMapping(value = "", produces = "application/json")
    @Valid
    public ArrayResponse<GenreResponse> getGenres() {
        return ArrayResponse.of(
                genreService.getAllGenres(),
                GenreResponse::new
        );
    }
}
