package thetestingacademy.sep05;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import thetestingacademy.sep05.DeSer.Employee;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationScenario {
    Response response;
    ValidatableResponse validatableResponse;
    RequestSpecification requestSpecification;
    Integer bookingID;
    String token;
@Test(priority = 1)
@Description("Create Auth token")
public void createAuthToken(){
    //create token and get the token
    // Auth to get Token
    String payloadAuth = "{\n" +
            "    \"username\" : \"admin\",\n" +
            "    \"password\" : \"password123\"\n" +
            "}";
    requestSpecification = RestAssured.given();
    requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
    requestSpecification.basePath("/auth");
    requestSpecification.contentType(ContentType.JSON);
    requestSpecification.body(payloadAuth);
    response = requestSpecification.when().post();

    validatableResponse = response.then().log().all();
    validatableResponse.statusCode(200);
    //Extract token
    token = response.then().extract().path("token");
    System.out.println(token);

}
@Test(priority = 2)
@Description("Get booking Id")
public void createBookingID(){
    //Create POST Request and get booking id
    Map<String,Object> payload_Post = new LinkedHashMap();
    payload_Post.put("firstname","John");
    payload_Post.put("lastname", "Brown");
    payload_Post.put("totalprice", 111);
    payload_Post.put("depositpaid", true);
    payload_Post.put("additionalneeds", "Lunch");
    Map<String,Object> bookingDatesMap = new LinkedHashMap();
    bookingDatesMap.put("checkin", "2021-07-01");
    bookingDatesMap.put("checkout", "2021-07-01");

    payload_Post.put("bookingdates",bookingDatesMap);
    requestSpecification.basePath("booking");
    requestSpecification.contentType(ContentType.JSON);
    requestSpecification.body(payload_Post);
    response = requestSpecification.when().post();
    validatableResponse = response.then().log().all();
    validatableResponse.statusCode(200);
    bookingID = response.then().extract().path("bookingid");
    System.out.println("booking id is "+bookingID);
}
    @Test(priority = 3)
    @Description("Update the Booking Name, Get the Booking by Id and verify.")
    public void updateBookingName() {
        //Step3-Token ID and Booking use in PUT request
        Map<String,Object> payloadPutRequest = new LinkedHashMap();
        payloadPutRequest.put("firstname","Gangesh");
        payloadPutRequest.put("lastname", "Jha");
        payloadPutRequest.put("totalprice", 111);
        payloadPutRequest.put("depositpaid", true);
        payloadPutRequest.put("additionalneeds", "Lunch");
        Map<String,Object> bookingDatesMapput = new LinkedHashMap();
        bookingDatesMapput.put("checkin", "2021-07-01");
        bookingDatesMapput.put("checkout", "2021-07-01");

        payloadPutRequest.put("bookingdates",bookingDatesMapput);
        requestSpecification.basePath("booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        response = requestSpecification.when().body(payloadPutRequest).put();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        Gson gson=new Gson();
        String bookingStringPayload = gson.toJson(payloadPutRequest);
        System.out.println(bookingStringPayload);
        JsonPath jsonPath = JsonPath.from(bookingStringPayload);
        System.out.println(jsonPath.getString("firstname"));

        // Rest Assured - Matchers - 5%
        validatableResponse.body("firstname", Matchers.equalTo("Gangesh"));

        // TestNG - 80%
        Assert.assertEquals(jsonPath.getString("firstname"),"Gangesh");

        // AssertJ - 15%- (45%) 10K
        assertThat(jsonPath.getString("firstname")).isNotNull().isNotEmpty().isEqualTo("Gangesh");
    }
    @Test(priority = 4)
    @Description("Create a BOOKING, Delete It")
    public void deleteBooking(){
        requestSpecification.basePath("booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        response=requestSpecification.when().delete();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);
    }

    @Test(priority = 5)
    @Description("Create a Booking, Delete the Booking with Id and Verify using GET request that it should not exist.")
    public void fetchdataafterdeleteBooking(){
        requestSpecification.basePath("booking/"+bookingID);
        response=RestAssured.given().spec(requestSpecification)
                .when().get();
        validatableResponse= response.then().log().all();
        validatableResponse.statusCode(404);
    }
    @Test(priority = 6)
    @Description("Trying to Update on a Delete Id -> 404")
    public void updateDataAfterdeleteBooking(){
        Map<String,Object> payloadPutRequest = new LinkedHashMap();
        payloadPutRequest.put("firstname","Pramod");
        payloadPutRequest.put("lastname", "Dutta");
        payloadPutRequest.put("totalprice", 111);
        payloadPutRequest.put("depositpaid", true);
        payloadPutRequest.put("additionalneeds", "Lunch");
        Map<String,Object> bookingDatesMapput = new LinkedHashMap();
        bookingDatesMapput.put("checkin", "2021-07-01");
        bookingDatesMapput.put("checkout", "2021-07-01");

        payloadPutRequest.put("bookingdates",bookingDatesMapput);
        requestSpecification.basePath("booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        response = requestSpecification.when().body(payloadPutRequest).put();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(405);
    }
    @Test(priority = 7)
    @Description("Get an Existing Booking from Get All Bookings Ids , Update a Booking and Verify using GET by id.")
    public void getAllBooingIds(){
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        response = requestSpecification.when().get();
        validatableResponse = response.then().log().all();
        bookingID=response.then().extract().path("bookingid[2]");
        System.out.println("Booking id--->"+response.then().extract().path("bookingid[2]").toString());
        validatableResponse.statusCode(200);
        //Get Booking for specific booking id
        requestSpecification.basePath("booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        response = requestSpecification.when().get();
        validatableResponse = response.then().log().all();
        // Update a Booking and Verify using GET by id.
        Map<String,Object> payloadPutRequest = new LinkedHashMap();
    /*    payloadPutRequest.put("firstname","Josh");
        payloadPutRequest.put("lastname", "Allen");*/
        payloadPutRequest.put("totalprice", 121);
        payloadPutRequest.put("depositpaid", false);
        payloadPutRequest.put("additionalneeds", "Lunch");
    /*    Map<String,Object> bookingDatesMapput = new LinkedHashMap();
        bookingDatesMapput.put("checkin", "2018-01-01");
        bookingDatesMapput.put("checkout", "2019-01-01");

        payloadPutRequest.put("bookingdates",bookingDatesMapput);*/
        requestSpecification.basePath("booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        response = requestSpecification.when().body(payloadPutRequest).patch();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        Gson gson=new Gson();
        String bookingStringPayload = gson.toJson(payloadPutRequest);
        System.out.println(bookingStringPayload);
        JsonPath jsonPath = JsonPath.from(bookingStringPayload);
        System.out.println(jsonPath.getString("depositpaid"));

        // Rest Assured - Matchers - 5%
        validatableResponse.body("additionalneeds", Matchers.equalTo("Lunch"));

        // TestNG - 80%
        Assert.assertEquals(jsonPath.getString("depositpaid"),"false");

        // AssertJ - 15%- (45%) 10K
        assertThat(jsonPath.getString("totalprice")).isNotNull().isNotEmpty();



    }


    @Test(priority = 8)
    @Description( "Invalid Creation - enter a wrong payload or Wrong JSON.")
    public void verifywithwrongPayload(){

        String payload_Post="{\n" +
                "   1233 \"firstname\" : \"Jim\",\n" +
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
        validatableResponse.statusCode(400);
    }
}

