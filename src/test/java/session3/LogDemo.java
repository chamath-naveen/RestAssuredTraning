package session3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class LogDemo {

    @Test
    void testLog(){

        baseURI = "https://www.google.com";
        given()
                .when()
                    .get(baseURI)
                .then()
                    .log().cookies()
                    .log().headers()
                    .log().status()
                    .log().body()
                    .log().all();
    }
}
