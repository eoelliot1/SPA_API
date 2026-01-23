package com.sparta.spa_api.restassured.RESTTrainerTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class DeleteTrainerByIdTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a trainer exists with ID 1
        When the user sends a DELETE request
        Then the API returns HTTP 200 or 204
        """)
    void shouldDeleteTrainerById() {

        RestAssured
                .given()
                .pathParam("id", 3)
                .when()
                .delete(APIConfig.TRAINER_BY_ID)
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no trainer exists with ID 999
        When the user sends a DELETE request
        Then the API returns HTTP 404
        """)
    void shouldReturn404WhenDeletingNonExistentTrainer() {

        RestAssured
                .given()
                .pathParam("id", 999)
                .when()
                .delete(APIConfig.TRAINER_BY_ID)
                .then()
                .statusCode(404);
    }
}
