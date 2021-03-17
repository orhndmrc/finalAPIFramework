package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import payloads.JiraPayloads;
import util.ConfigReader;
import util.TestUtil;

public class CreateBugJira extends TestUtil {
    public String token;
    @BeforeMethod
    public void setUp(){
        token = getAuthorizationKey();
    }
    @Test
    public void createBugOnJira(){
        createRequest(token).body(JiraPayloads.createBugPayload());
        executeAPI("POST", ConfigReader.getProperty("createBugPath"));
        responseSpecification(201);
        Response response = getResponse();
        String bugId = getJsonPath(response).getString("key");
        Assert.assertTrue(bugId.contains("TRY"));



    }

}
