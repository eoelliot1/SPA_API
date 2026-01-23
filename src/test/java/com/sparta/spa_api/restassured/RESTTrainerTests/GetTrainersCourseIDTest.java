package com.sparta.spa_api.restassured.RESTTrainerTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class GetTrainersCourseIDTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a trainer exists and is assigned to a course
        When the Course Administrator requests the trainer’s course
        Then the API should return HTTP 200
        And the response should contain the course details
        (Known defect: API currently returns HTTP 500)
        """)
    void shouldReturnCourseForTrainer() {

        RestAssured
                .given()
                .accept("application/json")
                .pathParam("id", 2)
                .when()
                .get(APIConfig.TRAINER_COURSES_BY_TRAINER)
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("courseName", notNullValue());
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no trainer exists with the requested ID
        When the Course Administrator requests the trainer’s course
        Then the API should return HTTP 404
        """)
    void shouldReturn404WhenTrainerDoesNotExist() {

        RestAssured
                .given()
                .accept("application/json")
                .pathParam("id", 999)
                .when()
                .get(APIConfig.TRAINER_COURSES_BY_TRAINER)
                .then()
                .statusCode(404);
    }
}
