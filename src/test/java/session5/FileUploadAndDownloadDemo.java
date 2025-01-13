package session5;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FileUploadAndDownloadDemo {

    @Test
    void testSingleFileUpload() {

        File uploadingFilename  = new File("C:\\RestAssuredTrainingFileUpload\\Test1.txt");

        given()
                .multiPart("file", uploadingFilename)
                .contentType("multipart/form-data")
                    .when()
                        .post("http://localhost:8080/uploadSingleFile")
                    .then()
                        .statusCode(200)
                        .body("fileName", equalTo("Test1.txt"))
                        .log().all();
    }

    @Test
    void testMultipleFileUpload() {
        File uploadingFilename1  = new File("C:\\RestAssuredTrainingFileUpload\\Test1.txt");
        File uploadingFilename2  = new File("C:\\RestAssuredTrainingFileUpload\\Test2.txt");

        given()
                .multiPart("files", uploadingFilename1)
                .multiPart("files", uploadingFilename2)
                .contentType("multipart/form-data")
                    .when()
                        .post("http://localhost:8080/uploadMultipleFiles")
                    .then()
                        .statusCode(200)
                        .body("[0]fileName", equalTo("Test1.txt"))
                        .body("[1]fileName", equalTo("Test2.txt"))
                        .log().all();
    }

    @Test
    void testMultipleFileUploadWithArray() {
        File uploadingFilename1  = new File("C:\\RestAssuredTrainingFileUpload\\Test1.txt");
        File uploadingFilename2  = new File("C:\\RestAssuredTrainingFileUpload\\Test2.txt");

        File uploadingFilenameArray[] = {uploadingFilename1, uploadingFilename2};

        given()
                .multiPart("files", uploadingFilenameArray)
                .contentType("multipart/form-data")
                    .when()
                        .post("http://localhost:8080/uploadMultipleFiles")
                    .then()
                        .statusCode(200)
                        .body("[0]fileName", equalTo("Test1.txt"))
                        .body("[1]fileName", equalTo("Test2.txt"))
                        .log().all();
    }

    @Test
    void testFileDownload() {
        given()
                .when()
                    .get("http://localhost:8080/downloadFile/Test1.txt")
                .then()
                    .statusCode(200)
                    .log().all();
    }
}
