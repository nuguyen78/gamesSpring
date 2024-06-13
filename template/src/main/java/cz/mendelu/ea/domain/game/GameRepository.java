package cz.mendelu.ea.domain.game;

import cz.mendelu.ea.domain.category.Category;
import cz.mendelu.ea.domain.genre.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, Integer> {

    List<Game> findAll();
    List<Game> findGameById(int id);
    /*List<Genre> findAllGenres();
    /*List<Category> findAllCategories();*/

   /* @Query("""
        SELECT COUNT(*) AS countOfFestivalsWhereBandNameStartsWithU
        FROM Game
        WHERE name LIKE 'U%' group by studio.id
    """)
    int countOfFestivalsWhereBandNameStartsWithU();*/
}
