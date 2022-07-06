package com.petstore.api.applicationapi;

import com.petstore.api.BaseApi;
import com.petstore.model.user.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;

import static com.petstore.api.Route.USER_API_BASE_URL;


public class UserApi {

    @Step
    public static Response post(User requestUser) {
        return BaseApi.post(requestUser, USER_API_BASE_URL);
    }

    public static Response post(List<User> requestUserList, String path) {
        return BaseApi.post(requestUserList, USER_API_BASE_URL + path);
    }

    public static Response get(HashMap<String, String> userCredentials, String path) {
        return BaseApi.get(userCredentials, USER_API_BASE_URL + path);
    }

    public static Response get(String path) {
        return BaseApi.get(USER_API_BASE_URL + path);
    }

    public static Response put(User requestUser, String path) {
        return BaseApi.put(requestUser, USER_API_BASE_URL + path);
    }

    @Step
    public static Response delete(String path) {
        return BaseApi.delete(USER_API_BASE_URL + path);
    }
}
