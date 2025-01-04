package session4;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseDataDemo {

    @Test
    void testJsonResponseDataApproachOne(){

        baseURI = "https://reqres.in/";
        basePath = "api/";

        given()
                //.contentType("application/json; charset=utf-8")
                .pathParam("pathParam" , "users")
                .queryParam("page", 2)
                    .when()
                        .get(baseURI + basePath + "{pathParam}")
                    .then()
                        .statusCode(200)
                        .header("Content-Type", "application/json; charset=utf-8")
                        .body("data[5].email", equalTo("rachel.howell@reqres.in"));
    }

    @Test
    void testJsonResponseDataApproachTwo() {

        baseURI = "https://reqres.in/";
        basePath = "api/";

        Response response = given()
                .contentType("application/json; charset=utf-8")
                .pathParam("pathParam" , "users")
                .queryParam("page", 2)
                    .when()
                        .get(baseURI + basePath + "{pathParam}");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
        Assert.assertEquals(response.jsonPath().get("data[5].email").toString(), "rachel.howell@reqres.in");
    }

    @Test
    void testJsonResponseDataApproachTwoEnhance() {

        baseURI = "https://reqres.in/";
        basePath = "api/";
        String email;
        boolean status = false;

        Response response = given()
                .contentType("application/json; charset=utf-8")
                .pathParam("pathParam" , "users")
                .queryParam("page", 2)
                .when()
                .get(baseURI + basePath + "{pathParam}");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject(response.asString());

        for (int i=0; i<jsonObject.getJSONArray("data").length(); i++){
            email = jsonObject.getJSONArray("data").getJSONObject(i).get("email").toString();
            //System.out.println(email);
            if(email.equals("rachel.howell@reqres.in")){
                status = true;
                break;
            }
        }
        Assert.assertEquals(status, true, "Email not found");
    }
}
