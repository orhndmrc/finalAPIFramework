package payloads;

import pojo.Location;
import pojo.RahulAPIPojo;

import java.util.ArrayList;
import java.util.List;

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
    public static RahulAPIPojo addNewPlacePayload2(){
        RahulAPIPojo place = new RahulAPIPojo();
        place.setAccuracy(5);
        place.setName("Sky House");
        place.setPhone_number("783445u495");
        place.setAddress("1 New York Street");
        place.setWebsite("king@gmail.com");
        place.setLanguage("Turkish");


        List<String> typ = new ArrayList<>();
        typ.add("apartment");
        typ.add("condo");
        place.setTypes(typ);


        Location loc = new Location();
        loc.setLat(-23.678);
        loc.setLng(45.765);
        place.setLocation(loc);


        return place;

    }
}
