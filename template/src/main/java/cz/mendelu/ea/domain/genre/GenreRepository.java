package cz.mendelu.ea.domain.genre;

import cz.mendelu.ea.domain.game.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Integer>  {
    List<Genre> findAll();
}
