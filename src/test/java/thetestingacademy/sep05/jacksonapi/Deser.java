package thetestingacademy.sep05.jacksonapi;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Deser {
    public static void main(String[] args) throws IOException {
        String jsonstring="{\n" +
                "  \"firstName\" : \"Pramod\",\n" +
                "  \"lastName\" : \"Dutta\",\n" +
                "  \"gender\" : \"M\",\n" +
                "  \"age\" : 35,\n" +
                "  \"salary\" : 2220000.0,\n" +
                "  \"married\" : false\n" +
                "}";

        //To verify the response ,we use  Deserialization
        ObjectMapper objectMapper=new ObjectMapper();
        Employee employee = objectMapper.readValue(jsonstring, Employee.class);
        System.out.println(employee.getFirstName());

    }
}
