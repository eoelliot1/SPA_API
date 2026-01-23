package com.sparta.spa_api.restassured.RESTTrainerTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class UpdateTrainerNameTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a trainer exists with a valid ID
        And the request body contains a valid trainer name
        When the Course Administrator updates the trainer name
        Then the API returns HTTP 201
        And the response contains the updated trainer name
        (Known defect: trainer_name is returned as a quoted string)
        """)
    void shouldUpdateTrainerNameOnly() {

        String requestBody = "\"Updated Trainer Name 6\"";

        RestAssured
                .given()
                .contentType("application/json")
                .accept("application/hal+json")
                .pathParam("id", 3)
                .body(requestBody)
                .when()
                .put(APIConfig.TRAINER_UPDATE_NAME)
                .then()
                .statusCode(201)
                .body("id", equalTo(3))
                .body("trainer_name", equalTo("\"Updated Trainer Name 6\""));
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no trainer exists with the provided ID
        When the Course Administrator attempts to update the trainer name
        Then the API returns HTTP 404
        """)
    void shouldReturn404WhenTrainerDoesNotExist() {

        String requestBody = "\"Non Existent Trainer\"";

        RestAssured
                .given()
                .contentType("application/json")
                .accept("application/hal+json")
                .pathParam("id", 999)
                .body(requestBody)
                .when()
                .put(APIConfig.TRAINER_UPDATE_NAME)
                .then()
                .statusCode(404);
    }
}
