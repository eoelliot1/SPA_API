package com.sparta.spa_api.restassured.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
