package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.GoRestPayloads;

import static io.restassured.RestAssured.given;

public class GoRestAPITest {
    @Test
    public void getUsers(){
        //BDD approach
        //given()--> we create our request with details-->headers,query parameters,body
        //when()-->http type(get,post,put,...) and execute the request
        //then()--> getting response and do some validation(JsonPath, Hamcrest matcher, deserilization)
        RestAssured.baseURI="https://gorest.co.in";

        //log().all()--->we can see the entire proccess in console

        given().log().all().header("Accept","application/json").header("Content-Type","application/json")
                .header("Authorization","Bearer 44e66e9fb7f714874b09ed79987210545326360d759e67d85b97d56782b8f244")
                .when().get("/public-api/users").then().log().all().contentType(ContentType.JSON).statusCode(200);
    }
    @Test
    public void getSingleUser(){
        //BDD approach
        //given()--> we create our request with details-->headers,query parameters,body
        //when()-->http type(get,post,put,...) and execute the request
        //then()--> getting response and do some validation(JsonPath, Hamcrest matcher, deserilization)
        RestAssured.baseURI="https://gorest.co.in";

        //log().all()--->we can see the entire proccess in console

        String response =given().log().all().header("Accept","application/json").header("Content-Type","application/json")
                .header("Authorization","Bearer 44e66e9fb7f714874b09ed79987210545326360d759e67d85b97d56782b8f244").queryParam("id",28)
                .when().get("/public-api/users").then().log().all().statusCode(200).extract().asString();

        JsonPath js = new JsonPath(response);
        String actualName = js.getString("data[0].name");
        System.out.println(actualName);
        Assert.assertEquals(actualName,"Atreyi Mararpopo");

        int actualimit = js.getInt("meta.pagination.limit");
        Assert.assertEquals(actualimit,20);



    }
    @Test
    public void createUser(){
        RestAssured.baseURI="https://gorest.co.in";

        given().log().all().header("Accept","application/json").header("Content-Type","application/json")
                .header("Authorization","Bearer 44e66e9fb7f714874b09ed79987210545326360d759e67d85b97d56782b8f244")
                .body(GoRestPayloads.createUserPayload2())
                .when().post("/public-api/users").then().log().all().statusCode(200);


    }
    @Test
    public void updateSingleUser(){
        RestAssured.baseURI="https://gorest.co.in";
        given().log().all().header("Accept","application/json").header("Content-Type","application/json")
                .header("Authorization","Bearer 44e66e9fb7f714874b09ed79987210545326360d759e67d85b97d56782b8f244")
                .body(GoRestPayloads.updateUserPayload())
                .when().put("/public-api/users/2268").then().log().all().statusCode(200);
    }
    @Test
    public void deleteSingleUser(){
        RestAssured.baseURI="https://gorest.co.in";
        given().log().all().header("Accept","application/json").header("Content-Type","application/json")
                .header("Authorization","Bearer 44e66e9fb7f714874b09ed79987210545326360d759e67d85b97d56782b8f244")
                .when().delete("/public-api/users/2246").then().log().all();
    }


}
