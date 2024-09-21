package thetestingacademy.sep05.restassured.CRUD.POST.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class PUT_Request {
    Response response;
    ValidatableResponse validatableResponse;
    // Get a Token - POST
    // Create a Booking and get ID
    // ID and Token in the PUT request
    // Then PUT -> Verify

    // Make a Post request to the /auth - get Token and
    // pass the token to the put requests
    // id -> 576
    // make a put request with the token and validate the response
    @Test
    public void put_request(){
        //create token and get the token
        // Auth to get Token
        String payloadAuth = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        RequestSpecification requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payloadAuth);
        response = requestSpecification.when().post();

       validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        //Extract token
        String token = response.then().extract().path("token");
        System.out.println(token);


         //Create POST Request and get booking id
        String payload_Post="{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_Post);
        response=requestSpecification.when().post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        Integer bookingID=response.then().extract().path("bookingid");
        System.out.println(bookingID);

        //Step3-Token ID and Booking use in PUT request
        String payloadPutRequest = "{\n" +
                "    \"firstname\" : \"Shubham\",\n" +
                "    \"lastname\" : \"Singh\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification.basePath("booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payloadPutRequest);
        response=requestSpecification.when().put();
        validatableResponse=response.then().log().all();
       validatableResponse.statusCode(200);
       validatableResponse.body("firstname", Matchers.equalTo("Shubham"));

       //delete the booking
        requestSpecification.basePath("booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        response=requestSpecification.when().delete();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);


        //After delete -->GET request that it doesn’t exist. (404)
        requestSpecification.basePath("booking/"+bookingID);
        response=RestAssured.given().spec(requestSpecification)
                .when().get();
        validatableResponse= response.then().log().all();
        validatableResponse.statusCode(404);










    }
}
