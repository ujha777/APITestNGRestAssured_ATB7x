package thetestingacademy.sep05.JSONSchema;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

public class JSV {
    @Test
    public void JSValidation(){

        RestAssured.given().baseUri("https://jsonplaceholder.typicode.com/posts/2").given().when().get()
                .then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/thetestingacademy/sep05/JSONSchema/Schema.json")));

    }
}
