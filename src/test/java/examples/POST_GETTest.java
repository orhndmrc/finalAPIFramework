package examples;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.RahulPayloads;
import util.ConfigReader;
import util.TestUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POST_GETTest extends TestUtil {
    @Test
    public void createPlace(){
        //BDD approach
        //given--> we put details to create a request
        //when()--->http method and path parameter
        //then()--->Validations
        createRequestRahul().body(RahulPayloads.addNewPlacePayload());
        executeAPI("POST", ConfigReader.getProperty("postParamRahul"));
        responseSpecification(200);
        getResponse();


    }

    @Test
    public void getPlace(){
        createRequestRahul().queryParam("place_id","6d090041f59e3ee715494ec0cec4ad11");
        executeAPI("GET",ConfigReader.getProperty("getPathParamRahul"));
        responseSpecification(200);
        Response response = getResponse();
        JsonPath js =getJsonPath(response);
        Assert.assertEquals(js.getString("name"),"Snowy House");
        Assert.assertEquals(js.getInt("location.latitude"),-10);

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
