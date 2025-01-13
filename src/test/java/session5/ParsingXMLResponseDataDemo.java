package session5;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ParsingXMLResponseDataDemo {

    @Test
    void testXMLResponseDataApproachOne(){

        given()
                .when()
                    .get("https://665f6a70-74d6-4c57-8502-ba1f96d2e43b.mock.pstmn.io/")
                .then()
                .statusCode(200)
                .header("Content-Type","application/xml; charset=utf-8")
                .body("users.user[0].roles.role[0].name", equalTo("Admin"));
    }

    @Test
    void testXMLResponseDataApproachTwo(){

        Response response = given()
                .when()
                    .get("https://665f6a70-74d6-4c57-8502-ba1f96d2e43b.mock.pstmn.io/");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/xml; charset=utf-8");

        String role = response.xmlPath().get("users.user[0].roles.role[0].name").toString();
        Assert.assertEquals(role, "Admin");
    }

    @Test
    void testXMLResponseDataApproachTwoEnhanced(){

        boolean status = false;

        Response response = given()
                .when()
                .get("https://665f6a70-74d6-4c57-8502-ba1f96d2e43b.mock.pstmn.io");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/xml; charset=utf-8");

        XmlPath xmlPath = new XmlPath(response.asString());

        // Verify the number of users in the XML
        List<String> totalUsers = xmlPath.getList("users.user");
        Assert.assertEquals(totalUsers.size(), 2);

        // Verify the given user email is present in XML
        List<String> userEmails = xmlPath.getList("users.user.email");
        for (String userEmail : userEmails){
            if (userEmail.equals("adf@gmail.com")){
                status = true;
                break;
            }
        }
        Assert.assertEquals(status, true, "Email not found");
    }
}
