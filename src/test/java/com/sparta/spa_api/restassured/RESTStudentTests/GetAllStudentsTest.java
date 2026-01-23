package com.sparta.spa_api.restassured.RESTStudentTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetAllStudentsTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given students exist in the system
        When all students are requested
        Then the API returns HTTP 200
        And the response body is a non-empty collection
        """)
    void shouldReturnAllStudents() {

        RestAssured
                .given()
                .accept("application/json")
                .when()
                .get(APIConfig.ALL_STUDENTS)
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].id", notNullValue());
    }
}
