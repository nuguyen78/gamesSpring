package cz.mendelu.ea.domain.game;

import cz.mendelu.ea.domain.category.Category;
import cz.mendelu.ea.domain.genre.Genre;
import cz.mendelu.ea.domain.studio.Studio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, Integer> {

    List<Game> findAll();
    List<Game> findGameById(int id);

    @Query("SELECT g FROM Game g JOIN g.categories c WHERE c.id = :categoryId")
    List<Game> findByCategoryId(@Param("categoryId") int categoryId);

    List<Game> findByStudio(Studio studio);
    /*List<Genre> findAllGenres();
    /*List<Category> findAllCategories();*/

   /* @Query("""
        SELECT COUNT(*) AS countOfFestivalsWhereBandNameStartsWithU
        FROM Game
        WHERE name LIKE 'U%' group by studio.id
    """)
    int countOfFestivalsWhereBandNameStartsWithU();*/
}
