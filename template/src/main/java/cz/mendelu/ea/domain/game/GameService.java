package cz.mendelu.ea.domain.game;

import cz.mendelu.ea.domain.category.Category;
import cz.mendelu.ea.domain.category.CategoryRepository;
import cz.mendelu.ea.domain.genre.Genre;
import cz.mendelu.ea.domain.genre.GenreRepository;
import cz.mendelu.ea.domain.review.ReviewRepository;
import cz.mendelu.ea.domain.studio.Studio;
import cz.mendelu.ea.domain.studio.StudioRepository;
import cz.mendelu.ea.domain.studio.StudioService;
import cz.mendelu.ea.utils.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;

    private final StudioRepository studioRepository;

    private final ReviewRepository reviewRepository;

    private final GenreRepository genreRepository;

    private final CategoryRepository categoryRepository;

    public GameService(GameRepository gameRepository, StudioRepository studioRepository, ReviewRepository reviewRepository, GenreRepository genreRepository, CategoryRepository categoryRepository) {
        this.gameRepository = gameRepository;
        this.studioRepository = studioRepository;
        this.reviewRepository = reviewRepository;
        this.genreRepository = genreRepository;
        this.categoryRepository = categoryRepository;
    }


    /*public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();

        gameRepository.findAll().forEach(games::add);
        return games;
    }*/
    public List<Game> getAllGames(int page, int size) {
        List<Game> movies = new ArrayList<>();
        gameRepository.findAll().forEach(movies::add);
        int start = page * size;
        return movies.stream()
                .skip(start)
                .limit(size)
                .collect(Collectors.toList());
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


    // Method to retrieve top-rated games by genre ID
    public List<Game> getTopRatedGamesByGenre(Integer genreId) {
        // Retrieve all games
        List<Game> allGames = gameRepository.findAll();

        // Filter games by the specified genre ID and sort by metacritic score
        List<Game> topRatedGames = allGames.stream()
                .filter(game -> game.getGenres().stream().anyMatch(genre -> genre.getId() == genreId))
                .sorted(Comparator.comparingInt(Game::getMetacriticScore).reversed())
                .collect(Collectors.toList());

        return topRatedGames;
    }

    public Map<String, Game> getMostReviewedGameByCategory() {
        List<Category> categories = categoryRepository.findAll();
        Map<String, Game> mostReviewedGamesByCategory = new HashMap<>();

        for (Category category : categories) {
            Game mostReviewedGame = category.getGames().stream()
                    .max(Comparator.comparingInt(reviewRepository::countByGame))
                    .orElse(null);
            if (mostReviewedGame != null) {
                mostReviewedGamesByCategory.put(category.getName(), mostReviewedGame);
            }
        }

        return mostReviewedGamesByCategory;
    }

    public List<Game> getGamesByReleaseYearBetween(int minYear, int maxYear) {
        return gameRepository.findAll().stream()
                .filter(game -> {
                    int releaseYear = game.getReleaseDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().getYear();
                    return releaseYear >= minYear && releaseYear <= maxYear;
                })
                .collect(Collectors.toList());
    }

    public List<Game> getAllGamesInCategory(int categoryId) {
        return gameRepository.findByCategoryId(categoryId);
    }

    public Game getGameWithOldestStudio() {
        // Find the studio with the earliest foundedDate
        Studio oldestStudio = studioRepository.findAll().stream()
                .min(Comparator.comparing(Studio::getFoundedDate))
                .orElseThrow(() -> new NotFoundException("No studios found"));

        // Find the first game associated with this studio
        return gameRepository.findByStudio(oldestStudio).stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No games found for the oldest studio"));
    }

}
