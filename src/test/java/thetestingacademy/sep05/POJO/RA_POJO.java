package thetestingacademy.sep05.POJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class RA_POJO {
    @Test
    public void authRequest(){
     Auth auth = new Auth();
     auth.setUsername("admin");
     auth.setPassword("password123");

        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .when().body(auth).post().then().log().all().statusCode(200);

    }
}
