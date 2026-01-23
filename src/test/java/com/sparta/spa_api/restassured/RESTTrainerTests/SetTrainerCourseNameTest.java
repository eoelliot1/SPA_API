package com.sparta.spa_api.restassured.RESTTrainerTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class SetTrainerCourseNameTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a trainer exists with ID 1
        And the request body contains a valid courseName
        When the user sends a PUT request to set the course name
        Then the API returns HTTP 200
        And the response reflects the updated course association
        """)
    void shouldSetCourseNameForTrainer() {


        String requestBody = "Apii Automation";

        RestAssured
                .given()
                .contentType("application/json")
                .pathParam("id", 1)
                .body(requestBody)
                .when()
                .put(APIConfig.TRAINER_SET_COURSE_NAME)
                .then()
                .statusCode(201);
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no trainer exists with ID 999
        When the user sends a PUT request to set the course name
        Then the API returns HTTP 404
        """)
    void shouldReturn404WhenTrainerDoesNotExist() {

        String requestBody = "Api Automation";

        RestAssured
                .given()
                .contentType("application/json")
                .pathParam("id", 999)
                .body(requestBody)
                .when()
                .put(APIConfig.TRAINER_SET_COURSE_NAME)
                .then()
                .statusCode(404);
    }
}
