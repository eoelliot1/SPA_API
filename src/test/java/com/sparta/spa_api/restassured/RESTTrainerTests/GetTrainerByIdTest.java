package com.sparta.spa_api.restassured.RESTTrainerTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetTrainerByIdTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a trainer exists in the system with a valid ID
        When the Course Administrator sends a GET request to /trainers/{id}
        Then the API returns HTTP 200
        And the response contains the trainer details
        """)
    void shouldReturnTrainerById() {

        RestAssured
                .given()
                .accept("application/json")
                .pathParam("id", 2)
                .when()
                .get(APIConfig.TRAINER_BY_ID)
                .then()
                .statusCode(200)
                .body("id", equalTo(2))
                .body("trainer_name", notNullValue());
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no trainer exists with the requested ID
        When the Course Administrator sends a GET request to /trainers/{id}
        Then the API returns HTTP 404
        """)
    void shouldReturn404WhenTrainerDoesNotExist() {

        RestAssured
                .given()
                .pathParam("id", 999)
                .when()
                .get(APIConfig.TRAINER_BY_ID)
                .then()
                .statusCode(404);
    }
}
