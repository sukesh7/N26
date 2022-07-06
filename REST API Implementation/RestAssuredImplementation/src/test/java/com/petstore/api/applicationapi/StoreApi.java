package com.petstore.api.applicationapi;

import com.petstore.api.BaseApi;
import com.petstore.model.store.Order;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.petstore.api.Route.STORE_API_BASE_URL;


public class StoreApi {

    public static Response post(Order order, String path) {
        return BaseApi.post(order, STORE_API_BASE_URL + path);
    }

    public static Response get(HashMap<String, String> orderDetails, String path) {
        return BaseApi.get(orderDetails, STORE_API_BASE_URL + path);
    }

    public static Response get(String path) {
        return BaseApi.get(STORE_API_BASE_URL + path);
    }

    public static Response delete(HashMap<String, String> orderDetails, String path) {
        return BaseApi.delete(orderDetails, STORE_API_BASE_URL + path);
    }
}
