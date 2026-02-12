package com.automation.controller;

import com.automation.model.PersonResponse;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PersonController {
    public PersonResponse getPersons(int qty, String gender, String birth_start, String birth_end) {
        Response response = RestAssured.given()
        .baseUri("https://fakerapi.it/api/v2")
        .queryParam("_quantity", qty)
        .queryParam("_gender", gender)
        .queryParam("_birthday_start", birth_start)
        .queryParam("_birthday_end", birth_end)
        .get("/persons");

        return response.as(PersonResponse.class);
    }
}
