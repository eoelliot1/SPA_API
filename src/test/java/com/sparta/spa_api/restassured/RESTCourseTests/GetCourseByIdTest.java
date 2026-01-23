package com.sparta.spa_api.restassured.RESTCourseTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetCourseByIdTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a valid course ID exists
        When the course is requested by ID
        Then the API should return HTTP 200
        And return the matching course
        Known defect: endpoint returns HTTP 500
        """)
    void shouldReturnCourseById() {

        RestAssured
                .given()
                .accept("application/json")
                .pathParam("id", 2)
                .when()
                .get(APIConfig.COURSE_BY_ID)
                .then()
                .statusCode(200)
                .body("id", equalTo(2))
                .body("courseName", notNullValue());
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no course exists with ID 999
        When the user sends a GET request to /courses/999
        Then the API should return HTTP 404
        """)
    void shouldReturn404WhenCourseDoesNotExist() {

        RestAssured
                .given()
                .accept("application/json")
                .pathParam("id", 999)
                .when()
                .get(APIConfig.COURSE_BY_ID)
                .then()
                .statusCode(404);
    }
}
