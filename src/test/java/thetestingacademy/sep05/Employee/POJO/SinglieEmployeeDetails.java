package thetestingacademy.sep05.Employee.POJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class SinglieEmployeeDetails {
    @Test
    public void getSingleEmployeeDetails(){
        RestAssured.given()
                .baseUri("https://dummy.restapiexample.com/")
                .basePath("api/v1/employee/20")
                .contentType(ContentType.JSON)
                .when().get()
                .then().log().all().statusCode(200);

    }
}
