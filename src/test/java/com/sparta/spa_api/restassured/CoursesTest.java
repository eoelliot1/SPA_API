package com.sparta.spa_api.restassured;


import com.sparta.spa_api.restassured.pojos.Course;
import com.sparta.spa_api.restassured.utils.Config;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CoursesTest {

    private static Course createdCourse;
    private static Course fetchedCourse;
    private static Course updatedCourse;
    private static Course[] allCourses;
    private static Course[] searchResults;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = Config.get("base.url");
    }

    // ---------------------------------------------------------
    // POST /courses
    // ---------------------------------------------------------
    @BeforeAll
    static void createCourse() {
        String requestBody = """
                {
                    "name": "Automation Testing",
                    "duration": 10
                }
                """;

        createdCourse = RestAssured
                .given()
                .header("Content-Type", Config.get("content.type"))
                .body(requestBody)
                .when()
                .post("/courses")
                .then()
                .extract()
                .as(Course.class);
    }

    @Test
    @Order(1)
    @DisplayName("Created course has correct fields")
    void testCreatedCourse() {
        MatcherAssert.assertThat(createdCourse.getName(), Matchers.is("Automation Testing"));
        MatcherAssert.assertThat(createdCourse.getDuration(), Matchers.is(10));
        MatcherAssert.assertThat(createdCourse.getId(), Matchers.greaterThan(0));
    }

    // ---------------------------------------------------------
    // GET /courses/{id}
    // ---------------------------------------------------------
    @BeforeAll
    static void getCourseById() {
        fetchedCourse = RestAssured
                .given()
                .pathParam("id", createdCourse.getId())
                .when()
                .get("/courses/{id}")
                .then()
                .extract()
                .as(Course.class);
    }

    @Test
    @Order(2)
    @DisplayName("Fetched course matches created course")
    void testFetchedCourse() {
        MatcherAssert.assertThat(fetchedCourse.getId(), Matchers.is(createdCourse.getId()));
        MatcherAssert.assertThat(fetchedCourse.getName(), Matchers.is("Automation Testing"));
    }

    // ---------------------------------------------------------
    // GET /courses
    // ---------------------------------------------------------
    @BeforeAll
    static void getAllCourses() {
        allCourses = RestAssured
                .given()
                .when()
                .get("/courses")
                .then()
                .extract()
                .as(Course[].class);
    }

    @Test
    @Order(3)
    @DisplayName("All courses list is not empty")
    void testAllCourses() {
        MatcherAssert.assertThat(allCourses.length, Matchers.greaterThan(0));
    }

    // ---------------------------------------------------------
    // GET /courses/search
    // ---------------------------------------------------------
    @BeforeAll
    static void searchCourses() {
        searchResults = RestAssured
                .given()
                .queryParam("name", "Automation")
                .when()
                .get("/courses/search")
                .then()
                .extract()
                .as(Course[].class);
    }

    @Test
    @Order(4)
    @DisplayName("Search returns matching courses")
    void testSearchResults() {
        MatcherAssert.assertThat(searchResults.length, Matchers.greaterThan(0));
        MatcherAssert.assertThat(searchResults[0].getName(), Matchers.containsString("Automation"));
    }

    // ---------------------------------------------------------
    // PUT /courses/{id}
    // ---------------------------------------------------------
    @BeforeAll
    static void updateCourse() {
        String updateBody = """
                {
                    "name": "Updated Automation",
                    "duration": 12
                }
                """;

        updatedCourse = RestAssured
                .given()
                .header("Content-Type", Config.get("content.type"))
                .pathParam("id", createdCourse.getId())
                .body(updateBody)
                .when()
                .put("/courses/{id}")
                .then()
                .extract()
                .as(Course.class);
    }

    @Test
    @Order(5)
    @DisplayName("Updated course has new values")
    void testUpdatedCourse() {
        MatcherAssert.assertThat(updatedCourse.getName(), Matchers.is("Updated Automation"));
        MatcherAssert.assertThat(updatedCourse.getDuration(), Matchers.is(12));
    }

    // ---------------------------------------------------------
    // DELETE /courses/{id}
    // ---------------------------------------------------------
    @Test
    @Order(6)
    @DisplayName("Course can be deleted")
    void testDeleteCourse() {
        RestAssured
                .given()
                .pathParam("id", createdCourse.getId())
                .when()
                .delete("/courses/{id}")
                .then()
                .statusCode(204);
    }
}
