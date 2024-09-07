package thetestingacademy.sep05.POJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class RA_POJO_POST_Request {
    Integer bookingID;
    String token;
    //Create Token
    @Test(priority = 1)
    public void authRequest(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        token=RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .when().body(auth).post().then().extract().path("token");

    }
    //Get Booking ID
    @Test(priority = 2)
    public void postRequest(){
        BookingPOJO bookingPOJO=new BookingPOJO();
        bookingPOJO.setFirstname("Pramod");
        bookingPOJO.setLastname("Duuta");
        bookingPOJO.setTotalprice(123);
        bookingPOJO.setDepositpaid(true);
        bookingPOJO.setAdditionalneeds("Breakfast");
        CreateBookingdate_POJO createBookingdatePojo=new CreateBookingdate_POJO();
        createBookingdatePojo.setCheckin("2022-01-01");
        createBookingdatePojo.setCheckout("2022-01-10");
        bookingPOJO.setBookingdates(createBookingdatePojo);
        //System.out.println(bookingPOJO);


        bookingID = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .body(bookingPOJO)
                .when().body(bookingPOJO).post()
                .then().extract().path("bookingid");

    }

    @Test(priority = 3)
    public void patchRequest(){
        BookingPOJO bookingPOJO=new BookingPOJO();
        bookingPOJO.setFirstname("Shubam");
        bookingPOJO.setLastname("Sharma");
        bookingPOJO.setTotalprice(123);
        bookingPOJO.setDepositpaid(true);
        bookingPOJO.setAdditionalneeds("Breakfast");
        CreateBookingdate_POJO createBookingdatePojo=new CreateBookingdate_POJO();
        createBookingdatePojo.setCheckin("2022-01-01");
        createBookingdatePojo.setCheckout("2022-01-10");
        bookingPOJO.setBookingdates(createBookingdatePojo);

        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/"+bookingID)
                .cookie("token",token)
                .contentType(ContentType.JSON)
                .body(bookingPOJO)
                .when().body(bookingPOJO).patch()
                .then().log().all().statusCode(200);

    }
}
