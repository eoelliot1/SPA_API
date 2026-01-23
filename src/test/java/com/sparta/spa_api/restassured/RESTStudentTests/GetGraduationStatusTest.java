package com.sparta.spa_api.restassured.RESTStudentTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class GetGraduationStatusTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a student exists
        When the graduation status is requested
        Then the API returns HTTP 200
        And the response body is a boolean value
        """)
    void shouldReturnGraduationStatus() {

        RestAssured
                .given()
                .pathParam("id", 4)
                .when()
                .get(APIConfig.STUDENT_GRADUATED)
                .then()
                .statusCode(200)
                .body("$", anyOf(is(true), is(false)));
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no student exists with the provided ID
        When the graduation status is requested
        Then the API returns HTTP 404
        """)
    void shouldReturn404ForNonExistentStudentGraduationStatus() {

        RestAssured
                .given()
                .pathParam("id", 999)
                .when()
                .get(APIConfig.STUDENT_GRADUATED)
                .then()
                .statusCode(404);
    }
}
