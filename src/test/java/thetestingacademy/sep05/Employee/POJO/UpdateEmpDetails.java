package thetestingacademy.sep05.Employee.POJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class UpdateEmpDetails {
    @Test
    public void updatEmpDetails(){
        UpdateEmp updateEmp = new UpdateEmp();
        updateEmp.getName();
        updateEmp.getSalary();
        updateEmp.getAge();

        RestAssured.given()
                .baseUri("https://dummy.restapiexample.com/")
                .basePath("api/v1/update/646")
                .contentType(ContentType.JSON)
                .when().body(updateEmp).put()
                .then().log().all().statusCode(200);

    }
}
