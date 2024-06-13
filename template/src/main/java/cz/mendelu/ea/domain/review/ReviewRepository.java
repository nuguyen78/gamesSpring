package cz.mendelu.ea.domain.review;

import cz.mendelu.ea.domain.game.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
