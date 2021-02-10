package payloads;

import pojo.NewUserPojo;

public class GoRestPayloads {
    public static String createUserPayload(){
        return "{\n" +
                "\"name\":\"Orhan Demirci\",\n" +
                "\"email\":\"crazy_king@gmail.com\", \n" +
                "\"status\":\"Active\",\n" +
                "\"gender\":\"Male\"\n" +
                "}";
    }
    public static String updateUserPayload(){
        return "{\n" +
                "\"name\":\"Ali Demirci\",\n" +
                "\"email\":\"ali_demirci@gmail.com\", \n" +
                "\"status\":\"Active\",\n" +
                "\"gender\":\"Male\"\n" +
                "}";
    }
    public static NewUserPojo createUserPayload2(){
        NewUserPojo user = new NewUserPojo();
        user.setName("Orhan Yu");
        user.setEmail("hrfhd@outlook.com");
        user.setStatus("Active");
        user.setGender("Male");

        return user;
    }
}
