package session6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class SerializationDeserialization {

    @Test
    void convertPojoToJson() throws JsonProcessingException {

        // Create JAVA Object using POJO Class
        POJO_Student pojo_student = new POJO_Student();

        pojo_student.setName("Kevin");
        pojo_student.setLocation("USA");
        pojo_student.setPhone("001 1234 5678");

        String studentCourseArr[] = {"JAVA", "Project Management"};
        pojo_student.setCourses(studentCourseArr);

        // Convert Plain Old JAVA Object to JSON Object (Serialization)
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo_student);

        System.out.println(jsonData);
    }

    @Test
    void convertJsonToPojo() throws JsonProcessingException {

        // Create JSON data in string format
        String jsonData = "{\n" +
                "  \"name\" : \"Kevin\",\n" +
                "  \"location\" : \"USA\",\n" +
                "  \"phone\" : \"001 1234 5678\",\n" +
                "  \"courses\" : [ \"JAVA\", \"Project Management\" ]\n" +
                "}";

        // Convert JSON Object to Plain Old JAVA Object (De-Serialization)
        ObjectMapper objectMapper = new ObjectMapper();

        POJO_Student pojoStudent = objectMapper.readValue(jsonData, POJO_Student.class);

        System.out.println("Name: " + pojoStudent.getName());
        System.out.println("Location: " + pojoStudent.getLocation());
        System.out.println("Phone: " + pojoStudent.getPhone());
        System.out.println("Course 1: " + pojoStudent.getCourses()[0]);
        System.out.println("Course 2: " + pojoStudent.getCourses()[1]);
    }
}
