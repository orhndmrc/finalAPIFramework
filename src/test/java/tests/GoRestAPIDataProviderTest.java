package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import payloads.GoRestPayloads;
import util.ConfigReader;
import util.TestUtil;

public class GoRestAPIDataProviderTest extends TestUtil {
    @Test(dataProvider = "getUsers")
    public void createMultipleUsers(String name, String email, String status, String gender) {
        createRequest().body(GoRestPayloads.createMultipleUsersPayload(name, email, status, gender));
        executeAPI("POST", ConfigReader.getProperty("postPathParam"));
        responseSpecification(200);
        getResponse();

    }
    @DataProvider
    public Object[][] getUsers(){
        Object[][] data = {{"Jiayo Gao","gao@gmail.com","Inactive","Female"},{"Rushi Patel","rushi@outlook.com","Active","Male"}
                ,{"Jasmen Hayles","jasmen@yahoo.com","Inactive","Female"}};

        return data;
    }
}
