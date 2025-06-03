package com.paremal.lamda.practice;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringToJson {
    public static void main(String[] args) {

        String  str ="{"
                + "  \"geodata\": ["
                + "    {"
                + "      \"id\": \"1\","
                + "      \"name\": \"Julie Sherman\","
                + "      \"gender\" : \"female\","
                + "      \"latitude\" : \"37.33774833333334\","
                + "      \"longitude\" : \"-121.88670166666667\""
                + "    },"
                + "    {"
                + "      \"id\": \"2\","
                + "      \"name\": \"Johnny Depp\","
                + "      \"gender\" : \"male\","
                + "      \"latitude\" : \"37.336453\","
                + "      \"longitude\" : \"-121.884985\""
                + "    }"
                + "  ]"
                + "}";

        Gson gson= new Gson();
        JsonObject jsonObject=gson.fromJson(str,JsonObject.class);
        System.out.println(jsonObject.toString());
        str.indexOf("a");

        ObjectMapper objectMapper= new ObjectMapper();

        try {
            Object json=objectMapper.readValue(str,Object.class);
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }




        /*Gson gson = new Gson();
        JsonObject obj = gson.fromJson(str, JsonObject.class);
        System.out.println(obj.toString());*/



        //  console.log(obj.name, "is", obj.age);
    }
}
