package com.automation.tests.day2;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SpartanTests {

    String BASE_URL = "http://3.90.112.152:8000/";
    //URI (Uniform Resource Identifier) = URL + URN = http://www.google.com/index.html
    //URL (Uniform Resource Locator)    = http://www.google.com
    //URN (Uniform Resource Name)       = /index.html

    @Test
    @DisplayName("Get list of all spartans") //optional
    public void getAllSpartans(){
        //401 - unauthorized
        given().auth().basic("admin", "admin").baseUri(BASE_URL).when().get("/api/spartans").prettyPeek().then().statusCode(200);
    }

    @Test
    @DisplayName("Add new spartan")
    public void addSpartan() {
        String body = "{\"gender\": \"Male\", \"name\": \"FatihIsBack\",\"phone\": 1591724350}";
        File jsonFile = new File(System.getProperty("user.dir") + "/spartan.json");
                given().
                        contentType(ContentType.JSON).
                        auth().basic("admin", "admin").
                        body(jsonFile).
                        baseUri(BASE_URL).
                when().
                        post("/api/spartans").prettyPeek().
                then().
                        statusCode(200);
    }

    @Test
    @DisplayName("Delete a spartan")
    public void deleteSpartan(){
        given().auth().basic("admin", "admin").baseUri(BASE_URL).
                when().delete("/api/spartans/{id}", 737).prettyPeek().then().statusCode(204);
    }
}
