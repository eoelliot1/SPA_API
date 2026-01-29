package com.sparta.spa_api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class TestBase {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.reset();              // IMPORTANT
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "";        // IMPORTANT (prevents weird path concatenation)

        System.out.println("TEST PORT = " + port);
        System.out.println("BASE = " + RestAssured.baseURI + ":" + RestAssured.port);
    }
}
