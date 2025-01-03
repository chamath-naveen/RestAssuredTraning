package session1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HttpRequestsDemo {

    int id;

    // GET
    @Test(priority = 0)
    void testGetUser(){
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    // GET
    @Test(priority = 1)
    void testGetUsers() {
        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .body("page", equalTo(2))
                    .log().all();
    }

    // POST
    @Test(priority = 2)
    void testCreateUser() {
        HashMap data = new HashMap();
        data.put("name", "Chamath");
        data.put("job", "QA Engineer");

        id = given()
                .contentType("application/json")
                .body(data)
                    .when()
                        .post("https://reqres.in/api/user")
                        .jsonPath().getInt("id");
                    /*.then()
                        .statusCode(201)
                        .log().all();*/

        System.out.println("Id :" + id);
    }

    // PUT
    @Test(priority = 3,dependsOnMethods = {"testCreateUser"})
    void testUpdateUser() {
        HashMap data = new HashMap();
        data.put("name", "Chamath Naveen");
        data.put("job", "Senior QA Engineer");

        given()
                .contentType("application/json")
                .body(data)
                    .when()
                        .put("https://reqres.in/api/user/"+id)
                .then()
                    .statusCode(200)
                    .log().all();
    }

    // DELETE
    @Test(priority = 4, dependsOnMethods = {"testUpdateUser"})
    void testDeleteUser() {
        given()

                .when()
                    .delete("https://reqres.in/api/user/"+id)
                .then()
                    .statusCode(204)
                    .log().all();
    }
}
