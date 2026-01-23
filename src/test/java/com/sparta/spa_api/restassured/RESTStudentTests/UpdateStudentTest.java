package com.sparta.spa_api.restassured.RESTStudentTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UpdateStudentTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a valid student update payload
        When the student is updated by ID
        Then the API should return HTTP 200
        (Known defect: API currently returns HTTP 500)
        """)
    void shouldUpdateStudentById_defectExpected() {

        String body = """
            {
              "student_name": "AnythingHere"
            }
            """;

        RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .pathParam("id", 2)
                .body(body)
                .when()
                .put(APIConfig.STUDENT_BY_ID)
                .then()
                .statusCode(200); // intentionally failing
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no student exists with the provided ID
        When the student is updated by ID
        Then the API returns HTTP 404
        """)
    void shouldReturn404WhenUpdatingNonExistentStudent() {

        String body = """
            {
              "student_name": "Does Not Exist"
            }
            """;

        RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .pathParam("id", 999)
                .body(body)
                .when()
                .put(APIConfig.STUDENT_BY_ID)
                .then()
                .statusCode(404);
    }
}
