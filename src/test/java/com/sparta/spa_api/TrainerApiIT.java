package com.sparta.spa_api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.Map;

public class TrainerApiIT extends TestBase {

    @Test
    void testGetTrainers() {
        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .when()
                .get("/api/trainers")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Get trainer by ID")
    void shouldReturnTrainerById() {
        Response response =
                given()
                        .auth().preemptive().basic("sarah", "sarahpass")
                        .when()
                        .get("/api/trainers/1")
                        .then()
                        .statusCode(200)
                        .extract().response();

        assertThat(response.jsonPath().getInt("id"), is(1));
    }

    @Test
    @DisplayName("Return 404 when trainer does not exist")
    void shouldReturn404ForInvalidTrainerId() {
        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .accept(ContentType.JSON)
                .when()
                .get("/api/trainers/999")
                .then()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body("status", equalTo(404))
                .body("message", notNullValue())
                .body("path", equalTo("/api/trainers/999"));
    }

    @Test
    @DisplayName("Get trainer by invalid ID → 404")
    void shouldReturn404WhenTrainerNotFound() {
        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .when()
                .get("/api/trainers/50")
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Create new trainer")
    void shouldCreateTrainer() {
        Map<String, Object> newTrainer = new HashMap<>();
        newTrainer.put("id", 8);
        newTrainer.put("trainerName", "Philip");
        newTrainer.put("courseId", 1);

        Response response =
                given()
                        .auth().preemptive().basic("sarah", "sarahpass")
                        .contentType(ContentType.JSON)
                        .body(newTrainer)
                        .when()
                        .post("/api/trainers")
                        .then()
                        .statusCode(201)
                        .extract().response();

        assertThat(response.jsonPath().getString("trainerName"), is("Philip"));
        assertThat(response.jsonPath().getInt("id"), is(8));
    }

    @Test
    @DisplayName("Update trainer")
    void shouldUpdateTrainer() {
        Map<String, Object> updatedTrainer = new HashMap<>();
        updatedTrainer.put("trainerName", "Cathy Updated");
        updatedTrainer.put("courseId", 2);

        Response response =
                given()
                        .auth().preemptive().basic("sarah", "sarahpass")
                        .contentType(ContentType.JSON)
                        .body(updatedTrainer)
                        .when()
                        .put("/api/trainers/1")
                        .then()
                        .statusCode(200)
                        .extract().response();

        assertThat(response.jsonPath().getString("trainerName"), is("Cathy Updated"));
        assertThat(response.jsonPath().getInt("courseId"), is(2));
    }

    @Test
    @DisplayName("Delete trainer")
    void shouldDeleteTrainer() {
        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .when()
                .delete("/api/trainers/1")
                .then()
                .statusCode(204);

        given()
                .auth().preemptive().basic("sarah", "sarahpass")
                .when()
                .get("/api/trainers/1")
                .then()
                .statusCode(404);
    }
}
