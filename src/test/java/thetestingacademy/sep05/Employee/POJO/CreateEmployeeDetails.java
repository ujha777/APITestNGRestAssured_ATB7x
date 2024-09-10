package thetestingacademy.sep05.Employee.POJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CreateEmployeeDetails {
   @Test
    public void createEmpDetails(){
       Emp emp = new Emp();
       emp.getName();
       emp.getSalary();
       emp.getAge();

       RestAssured.given()
               .baseUri("https://dummy.restapiexample.com/")
               .basePath("api/v1/create")
               .contentType(ContentType.JSON)
               .when().body(emp).post()
               .then().log().all().statusCode(200);

   }
}
