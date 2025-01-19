package session6;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class XMLSchemaValidation {

    @Test
    void testXMLSchemaValidation() {
        given()
                .when()
                    .get("https://665f6a70-74d6-4c57-8502-ba1f96d2e43b.mock.pstmn.io")
                .then()
                    .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchemaValidationFiles/restAssuredTrainingUserXsdSchema.xsd"));
    }
}
