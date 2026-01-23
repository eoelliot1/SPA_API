package com.sparta.spa_api.restassured;

import com.sparta.spa_api.restassured.utils.APIConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseApiTest {

    @BeforeAll
    static void globalSetup() {
        RestAssured.baseURI = APIConfig.BASE_URI;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
