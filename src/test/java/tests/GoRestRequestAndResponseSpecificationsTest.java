package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.GoRestPayloads;
import util.TestUtil;

import static io.restassured.RestAssured.given;

public class GoRestRequestAndResponseSpecificationsTest extends TestUtil {
    @Test
    public void getSingleUser() {
        createRequest().queryParam("id",2267);
        executeAPI("GET","/public-api/users");
        responseSpecification(200);
        Response response = getResponse();
        JsonPath js = getJsonPath(response);
        Assert.assertEquals(js.getString("data[0].name"),"Orhan Yu");


    }
    @Test
    public void createUser(){

        createRequest().body(GoRestPayloads.createUserPayload2());
        executeAPI("POST","/public-api/users");
        responseSpecification(200);
        getResponse();
    }
    @Test
    public void updateSingleUser(){
       createRequest().body(GoRestPayloads.updateUserPayload());
       executeAPI("PUT","/public-api/users/2267");
       responseSpecification(200);
       getResponse();

    }

}
