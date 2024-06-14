package cz.mendelu.ea.domain.review;

import cz.mendelu.ea.domain.category.Category;
import cz.mendelu.ea.domain.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        reviewRepository.findAll().forEach(reviews::add);
        return reviews;
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviewsForGames(List<Game> games) {
        List<Review> reviews = new ArrayList<>();
        for (Game game : games) {
            reviews.addAll(reviewRepository.findByGame(game));
        }
        return reviews;
    }
}
