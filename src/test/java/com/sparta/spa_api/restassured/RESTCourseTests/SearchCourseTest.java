package com.sparta.spa_api.restassured.RESTCourseTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class SearchCourseTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given courses exist matching the search term
        When courses are searched by name
        Then the API returns HTTP 200
        And a non-empty list of matching courses is returned
        """)
    void shouldReturnMatchingCourses() {

        RestAssured
                .given()
                .accept("application/json")
                .queryParam("name", "Automation")
                .when()
                .get(APIConfig.COURSE_SEARCH)
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].courseName", notNullValue());
    }

    @Test
    @Tag("happy")
    @DisplayName("""
        Given no courses exist matching the search term
        When courses are searched by name
        Then the API returns HTTP 200
        And an empty list is returned
        """)
    void shouldReturnEmptyListWhenNoCoursesMatch() {

        RestAssured
                .given()
                .accept("application/json")
                .queryParam("name", "Data")
                .when()
                .get(APIConfig.COURSE_SEARCH)
                .then()
                .statusCode(200)
                .body("$", empty());
    }
}
