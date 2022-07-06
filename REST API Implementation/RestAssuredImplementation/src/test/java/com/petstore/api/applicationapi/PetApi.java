package com.petstore.api.applicationapi;

import com.petstore.api.BaseApi;
import com.petstore.model.pet.Pet;
import com.petstore.model.user.User;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import static com.petstore.api.Route.PET_API_BASE_URL;
import static com.petstore.api.Route.STORE_API_BASE_URL;


public class PetApi {

    public static Response post(Pet petDetails) {
        return BaseApi.post(petDetails, PET_API_BASE_URL);
    }

    public static Response post(String path) {
        return BaseApi.post(PET_API_BASE_URL + path);
    }

    public static Response postImage(String path, ContentType contentType, File file, HashMap<String, String> params) {
        return BaseApi.post(PET_API_BASE_URL + path, contentType, file, params);
    }

    public static Response get(HashMap<String, String> params, String path) {
        return BaseApi.get(params, PET_API_BASE_URL + path);
    }

    public static Response get(String path) {
        return BaseApi.get(PET_API_BASE_URL + path);
    }

    public static Response put(Pet petDetails) {
        return BaseApi.put(petDetails, PET_API_BASE_URL);
    }

    public static Response delete(HashMap<String, String> params, String path) {
        return BaseApi.delete(params, PET_API_BASE_URL + path);
    }
}
