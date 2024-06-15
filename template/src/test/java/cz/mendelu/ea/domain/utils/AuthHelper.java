package cz.mendelu.ea.utils;

import cz.mendelu.ea.domain.game.GameRequest;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.restassured.RestAssured.given;

@Service
public class AuthHelper {

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/token")
    private String tokenUri;

    public String login(String username, String password) {
        // call token endpoint using http request and get token
        //var gameRequest = new GameRequest("game", 2021, List.of("action", "adventure");

        return given()
                .contentType(ContentType.URLENC)
                .formParam("username", username)
                .formParam("password", password)
                .formParam("grant_type", "password")
                .formParam("client_id", clientId)
                .when()
                .post(tokenUri)
                .then()
                .statusCode(200)
                .extract()
                .path("access_token");
    }

    public String login(String username) {
        return login(username, "1234");
    }
}
