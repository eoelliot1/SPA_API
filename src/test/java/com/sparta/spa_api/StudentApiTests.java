package com.sparta.spa_api;
import com.sparta.spa_api.TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.Map;

public class StudentApiTests extends TestBase{

    @Test
    void testGetStudents() {
        given()
                .auth().preemptive().basic("trainer", "trainerpass")
                .when()
                .get("/api/students")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Get course by ID")
    void shouldReturnCourseById() {
        Response response =
                given()
                        .auth().preemptive().basic("trainer", "trainerpass")
                        .when()
                        .get("/api/students/1")
                        .then()
                        .statusCode(200)
                        .extract().response();

        assertThat(response.jsonPath().getInt("id"), is(1));
    }

    @Test
    @DisplayName("Get Student by invalid ID → 404")
    void shouldReturn404WhenStudentNotFound() {
        given()
                .auth().preemptive().basic("trainer", "trainerpass")
                .when()
                .get("/api/students/50")
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Create new Student")
    void shouldCreateStudent() {
        Map<String, Object> newStudent = new HashMap<>();
        newStudent.put("id", 5);
        newStudent.put("studentName", "Ahmed");
        newStudent.put("courseId", 2);
        newStudent.put("trainerId", 2);
        newStudent.put("graduated", false);


        Response response =
                given()
                        .auth().preemptive().basic("trainer", "trainerpass")
                        .contentType(ContentType.JSON)
                        .body(newStudent)
                        .when()
                        .post("/api/students")
                        .then()
                        .statusCode(201)
                        .extract().response();

        assertThat(response.jsonPath().getString("studentName"), is("Ahmed"));
        assertThat(response.jsonPath().getInt("id"), is(5));
    }

    @Test
    @DisplayName("Delete Student")
    void shouldDeleteStudent() {
        given()
                .auth().preemptive().basic("trainer", "trainerpass")
                .when()
                .delete("/api/students/2")
                .then()
                .statusCode(204);

        // Verify deletion
        given()
                .auth().preemptive().basic("trainer", "trainerpass")
                .when()
                .get("/api/students/2")
                .then()
                .statusCode(404);
    }


}
