package com.petstore.tests;

import com.petstore.api.applicationapi.StoreApi;
import com.petstore.model.store.Inventory;
import com.petstore.model.store.Order;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Pet Store")
@Feature("Store Api")
public class StoreTests {

    @Test(groups={"Smoke"})
    public void shouldBeAbleToPlaceAnOrderToStore() {
        Order order = getOrder();
        Order responseOrder = StoreApi.post(order, "/order").as(Order.class);
        assertOrderExpectations(order, responseOrder);
        deleteOrder(order);
    }

    @Test(groups={"Smoke"})
    public void shouldBeAbleToDeleteAnOrderWithOrderId() {
        Order order = createOrder();
        HashMap<String, String> orderDetails = new HashMap<>();
        orderDetails.put("orderId", String.valueOf(order.getId()));

        Response response = StoreApi.delete(orderDetails, "/order/" + order.getId());
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test(groups={"Smoke"})
    public void shouldBeAbleToRetrieveOrderWIthOrderId() {
        Order order = createOrder();
        HashMap<String, String> orderDetails = new HashMap<>();
        orderDetails.put("orderId", String.valueOf(order.getId()));

        Order responseOrder = StoreApi.get(orderDetails, "/order/" + order.getId()).as(Order.class);
        assertOrderExpectations(order, responseOrder);
    }


    @Test(groups={"Smoke"})
    public void shouldBeAbleToRetrieveTheStoreInventory() {
        Response response = StoreApi.get("/inventory");
        Inventory inventory = response.as(Inventory.class);

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
    }


    private Order getOrder() {
        return Order.builder()
                .id((int) new Date().getTime())
                .petId(12345)
                .quantity(7)
                .shipDate("2022-06-30T00:00:00.000+00:00")
                .status("approved")
                .complete(true)
                .build();

    }

    private Order createOrder() {
        Order order = getOrder();
        return StoreApi.post(order, "/order").as(Order.class);
    }

    private void deleteOrder(Order order) {
        HashMap<String, String> orderDetails = new HashMap<>();
        orderDetails.put("orderId", String.valueOf(order.getId()));

        Response response = StoreApi.delete(orderDetails, "/order/" + order.getId());
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
    }

    private void assertOrderExpectations(Order requestOrder, Order responseOrder) {
        assertThat(responseOrder.getId(), equalTo(requestOrder.getId()));
        assertThat(responseOrder.getPetId(), equalTo(requestOrder.getPetId()));
        assertThat(responseOrder.getQuantity(), equalTo(requestOrder.getQuantity()));
        assertThat(responseOrder.getShipDate(), equalTo(requestOrder.getShipDate()));
        assertThat(responseOrder.getStatus(), equalTo(requestOrder.getStatus()));
        assertThat(responseOrder.getComplete(), equalTo(requestOrder.getComplete()));
    }
}
