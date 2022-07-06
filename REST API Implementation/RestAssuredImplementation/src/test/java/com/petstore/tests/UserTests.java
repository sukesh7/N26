package com.petstore.tests;

import com.petstore.api.applicationapi.UserApi;
import com.petstore.model.user.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringContains.containsString;

@Epic("Pet Store")
@Feature("User Api")
public class UserTests {

    @Test(groups={"Smoke"})
    public void shouldBeAbleToCreateAUser() {
        User requestUser = getUser();
        User responseUser = UserApi.post(requestUser).as(User.class);
        assertUserExpectations(responseUser, requestUser);
        deleteUser(requestUser);
    }

    @Test(groups={"Smoke"})
    public void shouldBeAbleToLoginAndLogout() {
        User user = createUser();
        HashMap<String, String> userCredentials = new HashMap<>();
        userCredentials.put("username", user.getUsername());
        userCredentials.put("password", user.getPassword());

        String loginResponse = UserApi.get(userCredentials, "/login").asString();
        assertThat(loginResponse, containsString("Logged in user session:"));

        String logoutResponse = UserApi.get("/logout").asString();
        assertThat(logoutResponse, equalTo("User logged out"));
        deleteUser(user);
    }

    @Test(groups={"Smoke"})
    public void shouldBeAbleToRetrieveUserInformationWithUserId() {
        User user = createUser();
        HashMap<String, String> userCredentials = new HashMap<>();
        userCredentials.put("username", user.getUsername());

        User actualUser = UserApi.get(userCredentials, "/" + user.getUsername()).as(User.class);
        assertUserExpectations(user, actualUser);
        deleteUser(user);
    }

    @Test(groups={"Smoke"})
    public void shouldBeAbleToCreateListOfUsers() {
        User user = createUser();
        List<User> requestUserList = new ArrayList<>();
        requestUserList.add(user);

        Response response = UserApi.post(requestUserList, "/createWithList");

        List<User> responseUserList = Arrays.asList(response.getBody().as(User[].class));
        assertUserExpectations(responseUserList.get(0), requestUserList.get(0));
        deleteUser(user);
    }

    @Test(groups={"Smoke"})
    public void shouldBeAbleToUpdateTheUser() {
        User requestUser = createUser();
        requestUser.setFirstName("Williams");

        User responseUser = UserApi.put(requestUser, "/" + requestUser.getUsername()).as(User.class);
        assertUserExpectations(requestUser, responseUser);
        deleteUser(requestUser);
    }

    @Test(groups={"Smoke"})
    public void shouldBeAbleToDeleteTheUser() {
        User user = createUser();
        Response response = UserApi.delete("/" + user.getUsername());
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
    }

    private User getUser() {
        return User.builder()
                .id(50)
                .username("testUser" + new Date().getTime())
                .firstName("James")
                .lastName("Blunt")
                .email("testuser@test.com")
                .password("testPassword")
                .phone("123456789")
                .userStatus(1)
                .build();
    }

    private void assertUserExpectations(User expectedUser, User actualUser) {
        assertThat(actualUser.getId(), equalTo(expectedUser.getId()));
        assertThat(actualUser.getUsername(), equalTo(expectedUser.getUsername()));
        assertThat(actualUser.getFirstName(), equalTo(expectedUser.getFirstName()));
        assertThat(actualUser.getLastName(), equalTo(expectedUser.getLastName()));
        assertThat(actualUser.getEmail(), equalTo(expectedUser.getEmail()));
        assertThat(actualUser.getPassword(), equalTo(expectedUser.getPassword()));
        assertThat(actualUser.getPhone(), equalTo(expectedUser.getPhone()));
        assertThat(actualUser.getUserStatus(), equalTo(expectedUser.getUserStatus()));
    }

    private User createUser() {
        User user = getUser();
        User responseUser = UserApi.post(user).as(User.class);
        assertUserExpectations(responseUser, user);
        return user;
    }

    private void deleteUser(User user) {
        Response response = UserApi.delete("/" + user.getUsername());
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
    }
}
