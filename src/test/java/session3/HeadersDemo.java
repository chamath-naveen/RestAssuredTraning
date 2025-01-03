package session3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HeadersDemo {

    @Test
    void testHeaders(){

        baseURI = "https://www.google.com";
        given()
                .when()
                    .get(baseURI)
                .then()
                    .header("Content-Type", "text/html; charset=ISO-8859-1")
                    .header("Content-Encoding", "gzip")
                    .header("Server", "gws")
                .log().all();
    }

    @Test
    void testGetHeadersInfo(){

        baseURI = "https://www.google.com";

        Response response = given()
                .when()
                .get(baseURI);

        // Get single header info
        System.out.println("----- Get Single Header -----");
        String serverHeaderInfo = response.getHeader("Server");
        System.out.println("The value of Server header is = " + serverHeaderInfo);

        // Get all headers info
        System.out.println("----- Get Multiple Headers -----");
        Headers headers = response.getHeaders();
        //System.out.println(headers.toString());
        for (Header header : headers) {
            System.out.println(header.getName() + " = " + header.getValue());
        }
    }
}
