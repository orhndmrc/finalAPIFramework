package payloads;

public class RahulPayloads {
    public static String addNewPlacePayload(){
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -10,\n" +
                "    \"lng\": 50\n" +
                "  },\n" +
                "  \"accuracy\": 35,\n" +
                "  \"name\": \"Snowy House\",\n" +
                "  \"phone_number\": \"(+91) 986 35 56\",\n" +
                "  \"address\": \"4 hayles Street\",\n" +
                "  \"types\": [\n" +
                "    \"condo\",\n" +
                "    \"apartment\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }
}
