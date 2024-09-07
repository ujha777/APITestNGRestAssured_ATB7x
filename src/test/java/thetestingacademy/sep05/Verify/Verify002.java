package thetestingacademy.sep05.Verify;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.AssertJUnit;
import thetestingacademy.sep05.jacksonapi.Employee;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Verify002 {
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
        //20% we are using AssertJ , normally we use Assert which use 80%
        //employee-first name should be pramod,not null should be as string
        assertThat(employee.getFirstName()).isNotNull().isNotBlank().contains("Pramod").isNotEmpty();
        System.out.println(employee.getFirstName());

        List<String> names= Arrays.asList("John","Joe","Johny");
        assertThat(names).hasSize(3).contains("Joe").doesNotContain("Dutta");

        LocalDate localDate=LocalDate.now();
        System.out.println(localDate);

        assertThat(localDate).isAfterOrEqualTo(LocalDate.of(2024,07,23))
                .isBeforeOrEqualTo(LocalDate.of(2024,10,22))
                .isBetween(LocalDate.of(2024,07,23),
                LocalDate.of(2024,10,22));


        File file=new File("D://My_Folder//db.json");
        assertThat(file).exists().isFile().canRead();


    }
    }
