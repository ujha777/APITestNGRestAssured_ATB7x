package thetestingacademy.sep05.Employee.POJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class DeleteEmpDetails {
    @Test
    public void deleteEmpDetails(){
        RestAssured.given()
                .baseUri("https://dummy.restapiexample.com/")
                .basePath("api/v1/delete/646")
                .contentType(ContentType.JSON)
                .when().delete()
                .then().log().all().statusCode(200);
    }
}
