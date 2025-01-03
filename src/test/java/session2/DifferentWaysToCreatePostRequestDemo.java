package session2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DifferentWaysToCreatePostRequestDemo {

    // Post Request body using Hashmap
    @Test
    void testUsingHashMap(){
        HashMap studentsData = new HashMap();

        studentsData.put("name", "Sea");
        studentsData.put("location", "Japan");
        studentsData.put("phone", "090 1234 5678");

        String studentCourseArr[] = {"Python", "Project Management"};

        studentsData.put("courses", studentCourseArr);

        given()
                .contentType("application/json")
                .body(studentsData)
                    .when()
                        .post("http://localhost:3000/students")
                    .then()
                        .statusCode(201)
                        .body("name", equalTo("Sea"))
                        .body("location", equalTo("Japan"))
                        .body("phone", equalTo("090 1234 5678"))
                        .body("courses[0]", equalTo("Python"))
                        .body("courses[1]", equalTo("Project Management"))
                        .header("Content-Type", "application/json")
                        .log().all();
    }

    // Post Request body using org.json library
    @Test
    void testUsingOrgJsonLibrary(){

        JSONObject studentsData = new JSONObject();

        studentsData.put("name", "Sea");
        studentsData.put("location", "Japan");
        studentsData.put("phone", "090 1234 5678");

        String studentCourseArr[] = {"Python", "Project Management"};

        studentsData.put("courses", studentCourseArr);

        given()
                .contentType("application/json")
                .body(studentsData.toString())
                    .when()
                        .post("http://localhost:3000/students")
                    .then()
                        .statusCode(201)
                        .body("name", equalTo("Sea"))
                        .body("location", equalTo("Japan"))
                        .body("phone", equalTo("090 1234 5678"))
                        .body("courses[0]", equalTo("Python"))
                        .body("courses[1]", equalTo("Project Management"))
                        .header("Content-Type", "application/json")
                        .log().all();
    }

    // Post Request body using POJO class
    @Test
    void testUsingPOJOClass(){

        POJO_PostRequest pojo_postRequest = new POJO_PostRequest();

        pojo_postRequest.setName("Sea");
        pojo_postRequest.setLocation("Japan");
        pojo_postRequest.setPhone("090 1234 5678");

        String studentCourseArr[] = {"Python", "Project Management"};

        pojo_postRequest.setCourses(studentCourseArr);

        given()
                .contentType("application/json")
                .body(pojo_postRequest)
                    .when()
                        .post("http://localhost:3000/students")
                .then()
                    .statusCode(201)
                    .body("name", equalTo("Sea"))
                    .body("location", equalTo("Japan"))
                    .body("phone", equalTo("090 1234 5678"))
                    .body("courses[0]", equalTo("Python"))
                    .body("courses[1]", equalTo("Project Management"))
                    .header("Content-Type", "application/json")
                    .log().all();
    }

    // Post Request body using external json file
    @Test
    void testUsingExternalJsonFile(){

        try {
            File file = new File(".\\studentsData.json");

            FileReader fileReader = new FileReader(file);

            JSONTokener jsonTokener = new JSONTokener(fileReader);

            JSONObject studentsData = new JSONObject(jsonTokener);

            given()
                    .contentType("application/json")
                    .body(studentsData.toString())
                        .when()
                            .post("http://localhost:3000/students")
                        .then()
                            .statusCode(201)
                            .body("name", equalTo("Sea"))
                            .body("location", equalTo("Japan"))
                            .body("phone", equalTo("090 1234 5678"))
                            .body("courses[0]", equalTo("Python"))
                            .body("courses[1]", equalTo("Project Management"))
                            .header("Content-Type", "application/json")
                            .log().all();
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}
