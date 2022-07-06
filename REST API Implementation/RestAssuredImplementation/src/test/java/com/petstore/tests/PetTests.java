package com.petstore.tests;

import com.google.common.primitives.Bytes;
import com.petstore.api.applicationapi.PetApi;
import com.petstore.api.applicationapi.StoreApi;
import com.petstore.api.applicationapi.UserApi;
import com.petstore.model.pet.Category;
import com.petstore.model.pet.Pet;
import com.petstore.model.pet.Tag;
import com.petstore.model.store.Inventory;
import com.petstore.model.store.Order;
import com.petstore.model.user.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@Epic("Pet Store")
@Feature("Pet Api")
public class PetTests {

    @Test(groups={"Smoke"})
    public void shouldBeAbleToAddAPet() {
        Pet pet = getPet();
        Response response = PetApi.post(pet);
        Pet responsePet = response.as(Pet.class);

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertExpectations(pet, responsePet);
        deletePet(pet);
    }

    @Test(groups={"Smoke"})
    public void shouldBeAbleToUpdateThePetDetailsWithFormParams() {
        Pet pet = createPet();
        String params = "?name=Dog5&status=Pending";
        Response response = PetApi.post("/" + pet.getId() + params);
        Pet responsePet = response.as(Pet.class);

        pet.setName("Dog5");
        pet.setStatus("Pending");
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertExpectations(pet, responsePet);
        deletePet(pet);
    }

    @Test(groups={"Regression"})
    public void shouldBeAbleToUploadAnImageToPet() {
        Pet pet = createPet();

        HashMap<String, String> params = new HashMap<>();
        params.put("petId", String.valueOf(pet.getId()));
        String path = "/" + pet.getId() + "/uploadImage";

        int photoUrlsCount = pet.getPhotoUrls().size();
        Response response =  PetApi.postImage(path, ContentType.BINARY, getImageFile(), params);
        Pet responsePet = response.as(Pet.class);
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertThat(responsePet.getPhotoUrls().size(), equalTo(photoUrlsCount + 1));
        deletePet(pet);
    }

    @Test(groups={"Smoke"})
    public void shouldBeAbleToUpdateThePetDetailsWithPayload() {
        Pet pet = createPet();
        pet.setName("Dog5");
        pet.setStatus("Pending");

        Response response =  PetApi.put(pet);
        Pet responsePet = response.as(Pet.class);

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertExpectations(pet, responsePet);
        deletePet(pet);
    }

    @Test(groups={"Smoke"})
    public void shouldBeAbleToRetrieveThePetByPetId() {
        Pet pet = createPet();
        Response response =  PetApi.get("/" + pet.getId());
        Pet responsePet = response.as(Pet.class);

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertExpectations(pet, responsePet);
        deletePet(pet);
    }

    @Test(groups={"Regression"})
    public void shouldBeAbleToRetrieveThePetByTag() {
        Pet pet = createPet();
        String tag = pet.getTags().get(0).getName();
        Response response = PetApi.get("/findByTags?tags=" + tag);
        List<Pet> responsePetList = Arrays.asList(response.getBody().as(Pet[].class));

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertThat("Failed to retrieve pet by Tag Id - " + tag, responsePetList.contains(pet));
        deletePet(pet);
    }

    @Test(groups={"Regression"})
    public void shouldBeAbleToRetrieveThePetByStatus() {
        Pet pet = createPet();
        String status = pet.getStatus();
        Response response = PetApi.get("/findByStatus?status=" + status);
        List<Pet> responsePetList = Arrays.asList(response.getBody().as(Pet[].class));

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertThat("Failed to retrieve pet by Status - " + status, responsePetList.contains(pet));
        deletePet(pet);
    }

    @Test(groups={"Regression"})
    public void shouldBeAbleToGetErrorResponseWhenInvalidStatusIsGivenAsInput() {
        Pet pet = createPet();
        String status = "InvalidStatus";
        Response response = PetApi.get("/findByStatus?status=" + status);
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));
        deletePet(pet);
    }


    private Pet getPet() {
        String[] status = {"available", "pending", "sold"};
        Category category = Category.builder()
                .id(1)
                .name("Dogs")
                .build();

        Tag tag = Tag.builder()
                .id(1)
                .name("tag1")
                .build();

        return Pet.builder()
                .id((int) new Date().getTime())
                .category(category)
                .name("Dog1")
                .photoUrls(Arrays.asList("url1", "url2"))
                .tags(Arrays.asList(tag))
                .status(status[new Random().nextInt(3)])
                .build();
    }

    private void assertExpectations(Pet request, Pet response) {
        assertThat(response.getId(), equalTo(request.getId()));
        assertThat(response.getCategory(), equalTo(request.getCategory()));
        assertThat(response.getName(), equalTo(request.getName()));
        assertThat(response.getPhotoUrls(), equalTo(request.getPhotoUrls()));
        assertThat(response.getTags(), equalTo(request.getTags()));
        assertThat(response.getStatus(), equalTo(request.getStatus()));
    }

    private Pet createPet() {
        Pet pet = getPet();
        return PetApi.post(pet).as(Pet.class);
    }

    private void deletePet(Pet pet) {
        HashMap<String, String> params = new HashMap<>();
        params.put("petId", String.valueOf(pet.getId()));

        Response response = PetApi.delete(params, "/" + pet.getId());
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertThat(response.asString(), equalTo("Pet deleted"));
    }

    private File getImageFile() {
        String path = System.getProperty("user.dir") + "/src/test/resources/data/image/cute_dog.PNG";
        return new File(path);
    }
}
