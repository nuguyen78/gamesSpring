package cz.mendelu.ea.domain.studio;

import cz.mendelu.ea.domain.game.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudioRepository extends CrudRepository<Studio, Integer> {

    List<Studio> findAll();
}
