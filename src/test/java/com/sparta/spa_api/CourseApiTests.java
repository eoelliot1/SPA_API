package com.sparta.spa_api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CourseApiTests extends TestBase {



    @Test
    @DisplayName("Get all courses")
    void shouldReturnAllCourses() {
        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .accept(ContentType.JSON)
                .when()
                .get("/api/courses")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].courseName", notNullValue());
    }

    @Test
    @DisplayName("Get course by ID")
    void shouldReturnCourseById() {
        Response response =
                given()
                        .auth().preemptive().basic("sarah", "sarahpass")
                        .accept(ContentType.JSON)
                        .when()
                        .get("/api/courses/1")
                        .then()
                        .statusCode(200)
                        .extract().response();

        assertThat(response.jsonPath().getInt("id"), is(1));
        assertThat(response.jsonPath().getString("courseName"), is("Software Testing"));
    }

    @Test
    @DisplayName("Return 404 when course does not exist")
    void shouldReturn404ForInvalidCourseId() {
        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .accept(ContentType.JSON)
                .when()
                .get("/api/courses/999")
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("message", containsString("Course not found"));
    }

    // ===================== POST =====================

    @Test
    @DisplayName("Create a new course")
    void shouldCreateCourse() {
        String requestBody = """
                {
                  "courseName": "Cloud Engineering",
                  "durationInDays": 180,
                  "startDate": "2025-02-01",
                  "endDate": "2025-07-31"
                }
                """;

        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/courses")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("courseName", is("Cloud Engineering"));
    }

    @Test
    @DisplayName("Return 400 when creating invalid course")
    void shouldReturn400ForInvalidCourse() {
        String requestBody = """
                {
                  "courseName": "",
                  "durationInDays": -5
                }
                """;

        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/courses")
                .then()
                .statusCode(400);
    }

    // ===================== PUT =====================

    @Test
    @DisplayName("Update an existing course")
    void shouldUpdateCourse() {
        String requestBody = """
                {
                  "courseName": "Advanced Software Testing",
                  "durationInDays": 500
                }
                """;

        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/api/courses/1")
                .then()
                .statusCode(200)
                .body("courseName", is("Advanced Software Testing"));
    }

    @Test
    @DisplayName("Return 404 when updating non-existent course")
    void shouldReturn404WhenUpdatingInvalidCourse() {
        String requestBody = """
                {
                  "courseName": "Ghost Course"
                }
                """;

        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/api/courses/99")
                .then()
                .statusCode(404)
                .body("status", is(404));
    }



    @Test
    @DisplayName("Delete a course")
    void shouldDeleteCourse() {
        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .when()
                .delete("/api/courses/2")
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("Return 404 when deleting non-existent course")
    void shouldReturn404WhenDeletingInvalidCourse() {
        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .when()
                .delete("/api/courses/99")
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("message", containsString("Course not found"));
    }
}
