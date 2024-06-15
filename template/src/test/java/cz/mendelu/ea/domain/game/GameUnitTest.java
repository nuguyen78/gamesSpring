package cz.mendelu.ea.domain.game;

import cz.mendelu.ea.domain.studio.Studio;
import cz.mendelu.ea.utils.response.ObjectResponse;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import cz.mendelu.ea.utils.response.ArrayResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)

public class GameUnitTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    private Game game1;
    private Game game2;

    @BeforeEach
    void setUp() {
        Studio studio1 = new Studio("Studio One", new Date(), 150);
        Studio studio2 = new Studio("Studio Two", new Date(), 80);
        game1 = new Game("Game One", new Date(), "An exciting adventure game.", "http://support.gameone.com", 85, studio1);
        game2 = new Game("Game Two", new Date(), "A thrilling action game.", "http://support.gametwo.com", 22, studio2);
    }

    @Test
    void testGetGames() {
        List<Game> games = Arrays.asList(game1, game2);
        when(gameService.getAllGames(0,10)).thenReturn(games);

        ArrayResponse<GameResponse> response = gameController.getGames(0, 10);

        verify(gameService).getAllGames(0, 10);
        assertThat(response.getItems().size(), is(2));
        assertThat(response.getItems().get(0).getName(), is("Game One"));
        assertThat(response.getItems().get(1).getName(), is("Game Two"));
    }

    @Test
    void testGetGameById() {
        when(gameService.findGameById(1)).thenReturn(game1);

        ObjectResponse<GameResponse> response = gameController.getGameById(1);

        verify(gameService).findGameById(1);
        assertThat(response.getContent().getName(), is("Game One"));
        assertThat(response.getContent().getMetacriticScore(), is(85));
    }
}
