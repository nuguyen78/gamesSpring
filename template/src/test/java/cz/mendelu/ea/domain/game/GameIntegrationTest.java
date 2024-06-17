package cz.mendelu.ea.domain.game;

import cz.mendelu.ea.domain.utils.AuthHelper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/test-data/cleanup.sql")
@Sql("/test-data/base-data.sql")
public class GameIntegrationTest {

    private final static String BASE_URI = "http://localhost";

    @Autowired
    private AuthHelper auth;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    @Test
    public void testGetGames() {
        when()
                .get("/games")
                .then()
                .statusCode(200)
                .body("items.size()", greaterThan(0));
    }

    @Test
    public void testGetGameById() {
        when()
                .get("/games/1")
                .then()
                .statusCode(200)
                .body("content.id", is(1))
                .body("content.name", is("Batman: Arkham Knight"));
    }

    @Test
    public void testGetGameById_NotFound() {
        when()
                .get("/games/999")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateGame() {
        var user = auth.login("petr");
        Integer id = given()
                .auth().oauth2(user)
                .contentType("application/json")
                .body("""
                        {
                            "name": "New Game",
                            "releaseDate": "2023-01-01",
                            "aboutTheGame": "This is a new game.",
                            "supportUrl": "http://example.com",
                            "metacriticScore": 85,
                            "studioId": 1,
                            "genreIds": [1, 2],
                            "categoryIds": [1, 2]
                        }
                        """)
                .when()
                .post("/games")
                .then()
                .statusCode(201)
                .body("content.name", is("New Game"))
                .extract()
                .path("content.id");

        when()
                .get("/games/{id}", id)
                .then()
                .statusCode(200)
                .body("content.id", is(id))
                .body("content.name", is("New Game"));
    }

    @Test
    public void testUpdateGame() {
        Integer id = given()
                .contentType("application/json")
                .body("""
                        {
                            "name": "Updated Game",
                            "releaseDate": "2023-01-01",
                            "aboutTheGame": "This is an updated game.",
                            "supportUrl": "http://example.com",
                            "metacriticScore": 90,
                            "studioId": 1,
                            "genres": [1, 3],
                            "categories": [2, 5]
                        }
                        """)
                .when()
                .put("/games/1")
                .then()
                .statusCode(200)
                .body("content.name", is("Updated Game"))
                .extract()
                .path("content.id");

        when()
                .get("/games/{id}", id)
                .then()
                .statusCode(200)
                .body("content.id", is(id))
                .body("content.name", is("Updated Game"));
    }

    @Test
    public void testDeleteGame() {
        given()
                .when()
                .delete("/games/1")
                .then()
                .statusCode(200)
                .body(is("Game was successfully deleted"));

        when()
                .get("/games/1")
                .then()
                .statusCode(404);
    }

    @Test
    public void testGetTopRatedGamesByGenre() {
        when()
                .get("/games/top-rated-by-genre/1")
                .then()
                .statusCode(200)
                .body("items.size()", greaterThan(0));
    }

    @Test
    public void testGetMostReviewedGameByCategory() {
        when()
                .get("/games/most-reviewed-per-category")
                .then()
                .statusCode(200)
                .body("content.size()", greaterThan(0));
    }

    @Test
    public void testGetGamesByReleaseYearBetween() {
        when()
                .get("/games/get-games-by-release-date-between/2020/2030")
                .then()
                .statusCode(200)
                .body("items.size()", greaterThan(0));
    }

    @Test
    public void testGetGameWithOldestStudio() {
        when()
                .get("/games/oldest-studio-game")
                .then()
                .statusCode(200)
                .body("content.id", notNullValue());
    }

}
