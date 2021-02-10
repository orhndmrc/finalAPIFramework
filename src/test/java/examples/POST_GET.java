package examples;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.RahulPayloads;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POST_GET {
    @Test
    public void createPlace(){
        //BDD approach
        //given--> we put details to create a request
        //when()--->http method and path parameter
        //then()--->Validations

        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().body(RahulPayloads.addNewPlacePayload()).queryParam("key","qaclick123")
                .when().post("/maps/api/place/add/json")
                .then().log().all().statusCode(200);

    }

    @Test
    public void getPlace(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response =given().log().all().queryParam("key","qaclick123").queryParam("place_id", "d90989f6052f800b47303a19f887e230")
                .when().get("/maps/api/place/get/json")
                .then().log().all().statusCode(200).extract().asString();

        JsonPath js = new JsonPath(response);//We use JsonPath to do validations

        String actualName = js.getString("name");
        Assert.assertEquals(actualName,"Snowy House");

        int lat = js.getInt("location.latitude");
        Assert.assertEquals(lat,-10);

    }
    @Test
    public void getPlaceHamcrestValidations(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
       given().log().all().queryParam("key","qaclick123").queryParam("place_id", "d90989f6052f800b47303a19f887e230")
                .when().get("/maps/api/place/get/json")
                .then().log().all().statusCode(200).body("name",equalTo("Snowy House"))
               .body("address",equalTo("4 hayles Street"));

       //HamcrestMatcher--->We do validations using hamcrest


    }
    @Test
    public void createNewPlaceWithPojo(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().body(RahulPayloads.addNewPlacePayload2()).queryParam("key","qaclick123")
                .when().post("/maps/api/place/add/json")
                .then().log().all().statusCode(200);
    }
}
