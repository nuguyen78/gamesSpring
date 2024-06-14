package cz.mendelu.ea.domain.category;

import cz.mendelu.ea.domain.game.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    List<Category> findAll();
}
