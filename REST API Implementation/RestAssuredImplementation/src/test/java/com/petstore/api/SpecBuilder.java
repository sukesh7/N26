package com.petstore.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.petstore.api.Route.BASE_PATH;
import static com.petstore.api.Route.BASE_URI;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpec(ContentType contentType) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .setConfig(RestAssuredConfig.newConfig().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .setContentType(contentType)
                .setAccept("*/*")
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
