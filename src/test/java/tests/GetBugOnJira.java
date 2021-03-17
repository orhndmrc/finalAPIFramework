package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.ConfigReader;
import util.TestUtil;

public class GetBugOnJira extends TestUtil {
    public String token;
    @BeforeMethod
    public void setup(){
         token = getAuthorizationKey();
    }
    @Test
    public void getSepecificBug(){
        createRequest(token);
        executeAPI("GET", ConfigReader.getProperty("getBugPath")+"/TRY-25");
        responseSpecification(200);
        Response response = getResponse();
        int avatarID = getJsonPath(response).getInt("fields.issuetype.avatarId");
        Assert.assertEquals(avatarID,10303);
    }
}
