package com.sparta.spa_api.restassured.RESTCourseTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class GetAllCoursesTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given courses exist in the system
        When all courses are requested
        Then the API returns HTTP 200
        And a non-null list of courses is returned
        """)
    void shouldReturnAllCourses() {

        RestAssured
                .given()
                .accept("application/json")
                .when()
                .get(APIConfig.ALL_COURSERS)
                .then()
                .statusCode(200)
                .body("$", notNullValue());
    }
}
