package com.sparta.spa_api.restassured.RESTCourseTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class CreateCourseTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given the request body contains a valid course name
        When the user sends a POST request to /courses
        Then the response status should be 201 or 200
        And the response body should contain id and courseName
        """)
    void shouldCreateCourseSuccessfully() {

        String body = """
            {
              "courseName": "Automation Course"
            }
            """;

        RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(APIConfig.ALL_COURSERS)
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("id", greaterThan(0))
                .body("courseName", equalTo("Automation Course"));
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given the request body is missing the required courseName
        When the user sends a POST request to /courses
        Then the response status should be 400
        """)
    void shouldFailToCreateCourseWithInvalidBody() {

        RestAssured
                .given()
                .contentType("application/json")
                .body("{}")
                .when()
                .post(APIConfig.ALL_COURSERS)
                .then()
                .statusCode(400);
    }
}
