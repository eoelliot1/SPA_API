package com.sparta.spa_api.restassured.RESTStudentTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetStudentByIdTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a student exists
        When the student is retrieved by ID
        Then the API returns HTTP 200
        And the student ID matches the request
        """)
    void shouldReturnStudentById() {

        RestAssured
                .given()
                .pathParam("id", 4)
                .accept("application/json")
                .when()
                .get(APIConfig.STUDENT_BY_ID)
                .then()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("hasGraduated", notNullValue());
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no student exists with the provided ID
        When the student is retrieved by ID
        Then the API returns HTTP 404
        """)
    void shouldReturn404WhenStudentNotFound() {

        RestAssured
                .given()
                .pathParam("id", 999)
                .accept("application/json")
                .when()
                .get(APIConfig.STUDENT_BY_ID)
                .then()
                .statusCode(404);
    }
}
