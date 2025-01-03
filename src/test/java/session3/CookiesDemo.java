package session3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class CookiesDemo {

    @Test
    void testCookies(){

        baseURI = "https://www.google.com";
        given()
                .when()
                    .get(baseURI)
                .then()
                    .cookie("AEC", "AZ6Zc-Ug3sowkSgfSj2SXnH1TG9oECak7-pSlfl5IY2wH-jZOFfEYLXPvQ")
                    .log().all();
    }

    @Test
    void testGetCookiesInfo(){

        baseURI = "https://www.google.com";

        Response response = given()
                                .when()
                                    .get(baseURI);

        // Get single cookie info
        System.out.println("----- Get Single Cookie -----");
        String aecCookieInfo = response.getCookie("AEC");
        System.out.println("The value of AEC cookie is = " + aecCookieInfo);

        // Get all cookies info
        System.out.println("----- Get Multiple Cookies -----");
        Map<String, String> cookiesInfo = response.getCookies();
        System.out.println(cookiesInfo.keySet());
        for (String key : cookiesInfo.keySet()) {
            String cookieInfo = response.getCookie(key);
            System.out.println(key + " " + cookieInfo);
        }
    }
}
