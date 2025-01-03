package session3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PathAndQueryParametersDemo {

    @Test
    void testPathAndQueryParameters(){

        baseURI = "https://reqres.in/";
        basePath = "api/";

        given()
                .pathParams("myPath", "users")        // Path parameter
                .queryParam("page", 2)           // Query parameter
                .queryParam("id", 4)             // Query parameter
                    .when()
                        .get(baseURI+basePath+"{myPath}")
                    .then()
                .statusCode(200)
                .log().all();
    }
}
