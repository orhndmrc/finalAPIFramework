package util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import payloads.JiraPayloads;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestUtil {
    RequestSpecification request;
    Response response;
    ResponseSpecification resspec;
    //The common methods are in order which means we use the methods the same order in test classes

    /**
     * This method helps us create the authorization token
     * @return
     */
    public String getAuthorizationKey(){
        Response response = given().log().all().body(JiraPayloads.tokenPayload())
                .header("Content-Type","application/json")
                .when().post(ConfigReader.getProperty("baseURI")+ConfigReader.getProperty("authPath"))
                .then().log().all().statusCode(200).extract().response();
        String sessionID = getJsonPath(response).getString("session.name");
        String sessionKey = getJsonPath(response).getString("session.value");
        return sessionID+"="+sessionKey;

    }

    /**
     * This method is for JiraAPI.RequestSpecification designates how the request looks like
     * @param
     * @return
     */
    public RequestSpecification createRequest(String token){
         request = given().log().all().spec(new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("baseURI"))
                .addHeader("Cookie", token)
                .setContentType(ContentType.JSON).build());
        return request;
    }

    public void executeAPI(String httpMethod, String pathParam ){
        switch(httpMethod){
            case "GET":
               response=request.when().get(pathParam);
                break;

            case "POST":
                response = request.when().post(pathParam);
                break;

            case "DELETE":
                response = request.when().delete(pathParam);
                break;

            case "PUT":
                response = request.when().put(pathParam);
                break;

            default:
                System.out.println("Please pass the correct http method");
                break;
        }
    }
    public ResponseSpecification responseSpecification(int statusCode){
        //ResponseSpecification designates how the response looks like
        resspec = new ResponseSpecBuilder().expectStatusCode(statusCode).expectContentType(ContentType.JSON).build();

        return resspec;
    }

    public Response getResponse(){
        return response.then().log().all().spec(resspec).extract().response();
    }

    public Response getResponseHamcrest(String key, String value){
        return response.then().log().all().spec(resspec).body(key,equalTo(value)).extract().response();
    }

    public JsonPath getJsonPath(Response response){
        return new JsonPath(response.asString());
    }




}
