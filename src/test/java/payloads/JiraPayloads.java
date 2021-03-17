package payloads;

import pojo.JiraTokenPojo;
import util.ConfigReader;

public class JiraPayloads {
    public static JiraTokenPojo tokenPayload(){
        JiraTokenPojo j = new JiraTokenPojo();
        j.setUsername(ConfigReader.getProperty("username"));
        j.setPassword(ConfigReader.getProperty("password"));
        return j;
    }

    public static String createBugPayload(){
        return "{\n" +
                "   \"fields\":{\n" +
                "       \"project\":{\n" +
                "           \"key\":\"TRY\"\n" +
                "       },\n" +
                "       \"summary\":\"Creating new issue on Jira board\",\n" +
                "       \"description\":\"My second issue\",\n" +
                "       \"issuetype\":{\n" +
                "           \"name\":\"Bug\"\n" +
                "       }\n" +
                "\n" +
                "   } \n" +
                "}";
    }
    public static String newBugPayload(){
        return "{\n" +
                "   \"fields\":{\n" +
                "       \"project\":{\n" +
                "           \"key\":\"TRY\"\n" +
                "       },\n" +
                "       \"summary\":\"Posting new issue on Jira board\",\n" +
                "       \"description\":\"New bug creation\",\n" +
                "       \"issuetype\":{\n" +
                "           \"name\":\"Bug\"\n" +
                "       }\n" +
                "\n" +
                "   } \n" +
                "}";
    }
}
