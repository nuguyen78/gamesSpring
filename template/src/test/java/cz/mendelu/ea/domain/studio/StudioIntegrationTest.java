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
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

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
    public void testGetById() {
        when()
                .get("/festivals/100")
                .then()
                .statusCode(200)
                .body("content.id", is(100))
                .body("content.name", is("Coachella"))
                .body("content.ticketPrice", is(2000.0F))
                .body("content.bandIds", containsInAnyOrder("292a485f-a56a-4938-8f1a-bbbbbbbbbbb1", "292a485f-a56a-4938-8f1a-bbbbbbbbbbb2"));
    }

    @Test
    public void testGetById_NotFound() {
        when()
                .get("/festivals/999")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreate() {
        Integer id = given()
                .contentType("application/json")
                .body("""
                        {
                            "name":"FestivalTest1",
                            "ticketPrice":1500.0,
                            "bandIds": ["292a485f-a56a-4938-8f1a-bbbbbbbbbbb1", "292a485f-a56a-4938-8f1a-bbbbbbbbbbb2"]
                        }
                        """)
                .when()
                .post("/festivals")
                .then()
                .statusCode(201)
                .body("content.name", is("FestivalTest1"))
                .body("content.ticketPrice", is(1500.0F))
                .body("content.bandIds", containsInAnyOrder("292a485f-a56a-4938-8f1a-bbbbbbbbbbb1", "292a485f-a56a-4938-8f1a-bbbbbbbbbbb2"))
                .extract()
                .path("content.id");

        when()
                .get("/festivals/{id}", id)
                .then()
                .statusCode(200)
                .body("content.id", is(id))
                .body("content.name", is("FestivalTest1"))
                .body("content.ticketPrice", is(1500.0F))
                .body("content.bandIds", containsInAnyOrder("292a485f-a56a-4938-8f1a-bbbbbbbbbbb1", "292a485f-a56a-4938-8f1a-bbbbbbbbbbb2"));
    }

    @Test
    public void testCreate_InvalidBandId() {
        given()
                .contentType("application/json")
                .body("""
                        {
                            "name":"FestivalTest1",
                            "ticketPrice": 1500.0,
                            "bandIds": ["292a485f-a56a-4938-8f1a-bbbbbbbbbbb1", "invalid-game-id"]
                        }
                        """)
                .when()
                .post("/festivals")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreate_InvalidTicketPrice() {
        given()
                .contentType("application/json")
                .body("""
                        {
                            "name":"FestivalTest1",
                            "ticketPrice": -100,
                            "bandIds": ["292a485f-a56a-4938-8f1a-bbbbbbbbbbb1", "292a485f-a56a-4938-8f1a-bbbbbbbbbbb2"]
                        }
                        """)
                .when()
                .post("/festivals")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreate_NonExistingBandId() {
        given()
                .contentType("application/json")
                .body("""
                        {
                            "name":"FestivalTest1",
                            "ticketPrice": 1500.0,
                            "bandIds": ["292a485f-a56a-4938-8f1a-bbbbbbbbbbb1", "99999999-9999-9999-9999-999999999999"]
                        }
                        """)
                .when()
                .post("/festivals")
                .then()
                .statusCode(404);
    }

    @Test
    public void testStatistics() {
        when()
                .get("/festivals/statistics")
                .then()
                .statusCode(200)
                .body("content.idsOfFestivalWithPriceBetween1500And2000", containsInAnyOrder(100))
                .body("content.countOfFestivalsWhereBandNameStartsWithU", is(1));
    }

}
