package cz.mendelu.ea.domain.review;

import cz.mendelu.ea.domain.game.Game;
import cz.mendelu.ea.domain.game.GameRequest;
import cz.mendelu.ea.domain.game.GameResponse;
import cz.mendelu.ea.domain.game.GameService;
import cz.mendelu.ea.utils.response.ArrayResponse;
import cz.mendelu.ea.utils.response.ObjectResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Validated
public class ReviewController {

    private final ReviewService reviewService;
    private final GameService gameService;

    @Autowired
    public ReviewController(ReviewService reviewService, GameService gameService) {
        this.reviewService = reviewService;
        this.gameService = gameService;
    }

    @GetMapping(value = "", produces = "application/json")
    @Valid
    public ArrayResponse<ReviewResponse> getReviews() {
        return ArrayResponse.of(
                reviewService.getAllReviews(),
                ReviewResponse::new
        );
    }

    @PostMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Valid
    public ObjectResponse<ReviewResponse> createReview(@RequestBody @Valid ReviewRequest reviewRequest) {
        Review review = new Review();
        reviewRequest.toReview(review, gameService);
        review = reviewService.createReview(review);
        return ObjectResponse.of(review, ReviewResponse::new);
    }

    @GetMapping(value = "/from-category/{categoryId}", produces = "application/json")
    @Valid
    public ArrayResponse<ReviewResponse> getAllReviewsFromCategory(@PathVariable int categoryId) {
        List<Game> gamesInCategory = gameService.getAllGamesInCategory(categoryId);
        List<Review> reviews = reviewService.getAllReviewsForGames(gamesInCategory);
        return ArrayResponse.of(
                reviews,
                ReviewResponse::new
        );
    }
}
