package thetestingacademy.sep05.Employee.POJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class AllEmployee_Details {
    @Test
    public void getallEmployeeDetails(){
        RestAssured.given()
                .baseUri("https://dummy.restapiexample.com/")
                .basePath("api/v1/employees")
                .contentType(ContentType.JSON)
                .when().get()
                .then().log().all().statusCode(200);

    }
}
