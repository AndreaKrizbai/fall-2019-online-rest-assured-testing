package com.automation.tests.day7;

import com.automation.pojos.Spartan;
import org.junit.jupiter.api.Test;
import com.automation.pojos.Employee;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicAuthentication {

    @Test
    public void spartanAuthentication(){
        //in the given part, we provide request specifications
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        given().
                auth().basic("user", "user").
                when().
                get("/spartans").prettyPeek().
                then().
                statusCode(200);
    }

    @Test
    public void authorizationTest(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        Spartan spartan = new Spartan("Araz", "Male", 34325689446155L);
                given().
                    auth().basic("user", "user").
                    body(spartan).
                    contentType(ContentType.JSON).
                when().
                    post("/spartans").prettyPeek().
                then().
                statusCode(403);
    }

    @Test
    public void authenticationTest(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        //if don't provide credentials, we must get 401 status code
        get("/spartans").prettyPeek().then().statusCode(401);
    }

    @Test
    public void authenticationTest2(){
        baseURI = "http://practice.cybertekschool.com";
        given().
                auth().basic("admin", "admin").
                when().
                get("/basic_auth").prettyPeek().
                then().
                statusCode(200).
                contentType(ContentType.HTML);
    }

}
