package com.sparta.spa_api.restassured.RESTCourseTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class DeleteCourseTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a course exists with ID 1
        When the user sends a DELETE request to /courses/1
        Then the response status should be 200 or 204
        """)
    void shouldDeleteExistingCourse() {

        RestAssured
                .given()
                .pathParam("id", 3)
                .when()
                .delete(APIConfig.COURSE_BY_ID)
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no course exists with ID 999
        When the user sends a DELETE request to /courses/999
        Then the response status should be 404
        """)
    void shouldReturn404ForNonExistentCourse() {

        RestAssured
                .given()
                .pathParam("id", 999)
                .when()
                .delete(APIConfig.COURSE_BY_ID)
                .then()
                .statusCode(404);
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        When the user sends a DELETE request with an invalid course ID format
        Then the response status should be 400
        """)
    void shouldReturn400ForInvalidCourseId() {

        RestAssured
                .when()
                .delete("/courses/abc")
                .then()
                .statusCode(400);
    }
}
