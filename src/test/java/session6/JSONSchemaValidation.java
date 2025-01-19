package session6;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class JSONSchemaValidation {

    @Test
    void testJSONSchemaValidation() {
        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaValidationFiles/restAssuredTrainingUserJsonSchema.json"));
    }
}
