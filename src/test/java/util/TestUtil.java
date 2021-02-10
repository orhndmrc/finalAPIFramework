package util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class TestUtil {
    RequestSpecification request;
    Response response;
    ResponseSpecification resspec;
    public RequestSpecification createRequest(){
        //RequestSpecification designates how the request looks like
         request = given().log().all().spec(new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
                .addHeader("Accept","application/json").addHeader("Authorization","Bearer 44e66e9fb7f714874b09ed79987210545326360d759e67d85b97d56782b8f244")
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

    public JsonPath getJsonPath(Response response){
        return new JsonPath(response.asString());
    }



}
