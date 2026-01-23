package com.sparta.spa_api.restassured.RESTTrainerTests;

import com.sparta.spa_api.restassured.BaseApiTest;
import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class UpdateTrainerByIdTest extends BaseApiTest {

    @Test
    @Tag("happy")
    @DisplayName("""
        Given a trainer exists with a valid ID
        And the request body matches the documented Trainer schema
        When the Course Administrator sends a PUT request to update the trainer
        Then the API returns HTTP 200
        And the response contains the updated trainer details
        (Known defect: API does not correctly handle full Trainer payload)
        """)
    void shouldUpdateTrainerById() {

        String requestBody = """
            {
              "id": 1,
              "trainer_name": "John Trainer",
              "course_id": {
                "id": 1,
                "courseName": "Data",
                "students": [
                  {
                    "id": 3,
                    "hasGraduated": false,
                    "course": "Data",
                    "student_name": "Test Student"
                  }
                ],
                "trainers": [
                  {
                    "id": 1,
                    "trainer_name": "Updated Trainer Name",
                    "course": "Automation"
                  }
                ]
              }
            }
            """;

        RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .pathParam("id", 1)
                .body(requestBody)
                .when()
                .put(APIConfig.TRAINER_BY_ID)
                .then()
                .statusCode(200)
                .body("trainer_name", equalTo("Updated Trainer Name"));
    }

    @Test
    @Tag("sad")
    @DisplayName("""
        Given no trainer exists with the provided ID
        And the request body matches the documented Trainer schema
        When the Course Administrator sends a PUT request to update the trainer
        Then the API returns HTTP 404
        """)
    void shouldReturn404WhenUpdatingNonExistentTrainer() {

        String requestBody = """
            {
              "id": 999,
              "trainer_name": "Does Not Exist",
              "course_id": {
                "id": 1,
                "courseName": "Automation",
                "students": [],
                "trainers": []
              }
            }
            """;

        RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .pathParam("id", 999)
                .body(requestBody)
                .when()
                .put(APIConfig.TRAINER_BY_ID)
                .then()
                .statusCode(404);
    }
}
