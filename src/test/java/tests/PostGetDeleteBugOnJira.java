package tests;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import payloads.JiraPayloads;
import util.ConfigReader;
import util.TestUtil;

public class PostGetDeleteBugOnJira extends TestUtil {
    public String token;
    String bugKey;
    @BeforeMethod
    public void setup(){
        token = getAuthorizationKey();
    }

    @Test(priority = 1)
    public void createBug(){
        createRequest(token).body(JiraPayloads.newBugPayload());
        executeAPI("POST", ConfigReader.getProperty("createBugPath"));
        responseSpecification(201);
        Response response = getResponse();
         bugKey = getJsonPath(response).getString("key");
    }
    @Test(priority = 2)
    public void getSpecificBug(){
        createRequest(token);
        executeAPI("GET",ConfigReader.getProperty("getBugPath")+"/"+bugKey);
        responseSpecification(200);
        getResponse();
    }
    @Test(priority = 3)
    public void deleteBug(){
        createRequest(token);
        executeAPI("DELETE",ConfigReader.getProperty("deleteBugPath")+"/"+bugKey);
        responseSpecification(204);
        getResponse();
    }
}
