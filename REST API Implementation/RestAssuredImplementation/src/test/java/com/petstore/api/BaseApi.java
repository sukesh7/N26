package com.petstore.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.petstore.api.SpecBuilder.getRequestSpec;
import static com.petstore.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class BaseApi {

    public static Response post(Object requestPayload, String path) {
        return given(getRequestSpec())
                .body(requestPayload)
                .when()
                .post(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response post(String path) {
        return given(getRequestSpec())
                .when()
                .post(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response post(String path, ContentType contentType, File file, Object params) {
        return given(getRequestSpec(contentType))
                .queryParam(String.valueOf(params))
                .body(file)
                .when()
                .post(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(Object params, String path) {
        return given(getRequestSpec())
                .param(String.valueOf(params))
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path) {
        return given(getRequestSpec())
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response put(Object requestPayload, String path) {
        return given(getRequestSpec())
                .body(requestPayload)
                .when()
                .put(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response delete(String path) {
        return given(getRequestSpec())
                .when()
                .delete(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response delete(Object params, String path) {
        return given(getRequestSpec())
                .param(String.valueOf(params))
                .when()
                .delete(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }
}
