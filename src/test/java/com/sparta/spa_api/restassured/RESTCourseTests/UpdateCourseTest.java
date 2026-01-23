package com.sparta.spa_api.restassured.RESTCourseTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class UpdateCourseTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a course exists with ID 1
        And the request body contains a valid updated course name
        When the user sends a PUT request to /courses/1
        Then the response status should be 200
        And the response body should contain id 1
        """)
    void shouldUpdateExistingCourse() {

        String body = """
            {
              "id": 2,
              "courseName": "Updated Course Name"
            }
            """;

        RestAssured
                .given()
                .contentType("application/json")
                .pathParam("id", 2)
                .body(body)
                .when()
                .put(APIConfig.COURSE_BY_ID)
                .then()
                .statusCode(200)
                .body("id", equalTo(2));
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no course exists with ID 999
        When the user sends a PUT request to /courses/999
        Then the response status should be 404
        """)
    void shouldReturn404ForNonExistentCourse() {

        String body = """
            {
              "courseName": "Does Not Exist"
            }
            """;

        RestAssured
                .given()
                .contentType("application/json")
                .pathParam("id", 999)
                .body(body)
                .when()
                .put(APIConfig.COURSE_BY_ID)
                .then()
                .statusCode(404);
    }
}
