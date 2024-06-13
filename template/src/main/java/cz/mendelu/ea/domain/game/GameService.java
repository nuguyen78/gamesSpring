package cz.mendelu.ea.domain.game;

import cz.mendelu.ea.domain.review.ReviewRepository;
import cz.mendelu.ea.domain.studio.StudioRepository;
import cz.mendelu.ea.domain.studio.StudioService;
import cz.mendelu.ea.utils.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    private final StudioRepository studioRepository;

    private final ReviewRepository reviewRepository;

    public GameService(GameRepository gameRepository, StudioRepository studioRepository, ReviewRepository reviewRepository) {
        this.gameRepository = gameRepository;
        this.studioRepository = studioRepository;
        this.reviewRepository = reviewRepository;
    }


    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
/*        if (games.isEmpty()) {
            throw new NotFoundException();
        }*/
        gameRepository.findAll().forEach(games::add);
        return games;
    }

    public Game findGameById(Integer id) {
        return gameRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Game game) {
        gameRepository.save(game);
        return game;
    }

    @Transactional
    public void deleteGame(Integer id){
        gameRepository.deleteById(id);
    }

/*    public Optional<Game> getGame(UUID id){
        return repository.findById(id);
    }*/

}
