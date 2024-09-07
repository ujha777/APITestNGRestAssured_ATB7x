package thetestingacademy.sep05.jacksonapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ser {
    public static void main(String[] args) throws JsonProcessingException {
        Employee employee=new Employee();
        employee.setFirstName("Pramod");
        employee.setLastName("Dutta");
        employee.setAge(35);
        employee.setSalary(2220000);
        employee.setMarried(false);
        employee.setGender("M");

        ObjectMapper objectMapper= new ObjectMapper();
        String jsonString=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
        System.out.println(jsonString);




    }
}
