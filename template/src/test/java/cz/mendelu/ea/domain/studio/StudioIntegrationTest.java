package cz.mendelu.ea.domain.studio;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class StudioIntegrationTest {

    private final static String BASE_URI = "http://localhost";

    @LocalServerPort
    private int port;

    @BeforeEach
    public void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    @Test
    public void testGetStudios() {
        when()
                .get("/studio")
                .then()
                .statusCode(200)
                .body("items.size()", greaterThan(0)); // Adjust based on your test data
    }


    @Test
    public void testGetStudioById() {
        when()
                .get("/studio/1")
                .then()
                .statusCode(200)
                .body("content.id", is(1));
    }

    @Test
    public void testGetGameById_NotFound() {
        when()
                .get("/studio/999")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateStudio() {
        int id = given()
                .contentType("application/json")
                .body("""
                        {
                            "name": "New Studio",
                            "foundedDate": "2023-01-01",
                            "numberOfWorkers": 25
                        }
                        """)
                .when()
                    .post("/studio")
                .then()
                    .statusCode(200)
                .extract()
                    .path("id");

        when()
                .get("/studio/{id}", id)
                .then()
                .statusCode(200)
                .body("content.id", is(id))
                .body("content.name", is("New Studio"));
    }

}
