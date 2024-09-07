package thetestingacademy.sep05.DeSer;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import thetestingacademy.sep05.POJO.BookingPOJO;
import thetestingacademy.sep05.POJO.CreateBookingdate_POJO;

public class Serialization {

    public static void main(String[] args) {
        //Json to String
        BookingPOJO booking = new BookingPOJO();
        booking.setFirstname("Pramod");
        booking.setLastname("Dutta");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("BreakFast");

        CreateBookingdate_POJO createBookingdatePojo=new CreateBookingdate_POJO();
        createBookingdatePojo.setCheckin("2022-01-01");
        createBookingdatePojo.setCheckout("2022-01-10");
        booking.setBookingdates(createBookingdatePojo);

        Gson gson=new Gson();
        String bookingStringPayload = gson.toJson(booking);
        System.out.println(bookingStringPayload);
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .when().body(bookingStringPayload).post()
                .then().log().all().statusCode(200);
    }



}
