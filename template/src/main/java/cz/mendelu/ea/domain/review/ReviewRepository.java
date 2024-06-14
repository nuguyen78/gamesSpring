package cz.mendelu.ea.domain.review;

import cz.mendelu.ea.domain.game.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, Integer> {

    @Query("SELECT COUNT(r) FROM Review r WHERE r.game = :game")
    int countByGame(@Param("game") Game game);

    @Query("SELECT r FROM Review r WHERE r.game = :game")
    List<Review> findByGame(@Param("game") Game game);
}
