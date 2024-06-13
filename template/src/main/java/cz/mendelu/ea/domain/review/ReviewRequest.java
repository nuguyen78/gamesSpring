package cz.mendelu.ea.domain.review;

import cz.mendelu.ea.domain.game.Game;
import cz.mendelu.ea.domain.game.GameService;
import cz.mendelu.ea.domain.studio.Studio;
import cz.mendelu.ea.utils.exceptions.NotFoundException;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ReviewRequest {

    @NotNull
    private int id;

    @NotEmpty
    private String text;

    @NotNull
    private int gameId;

    @NotNull
    private Date createdAt;


    public void toReview(Review review, GameService gameService) {
        review.setText(text);
        Game game = gameService.findGameById(gameId);
        if (game == null) {
            throw new NotFoundException();
        }
        review.setGame(game);
        review.setCreatedAt(createdAt);
    }
}
