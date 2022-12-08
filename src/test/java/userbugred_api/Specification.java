package userbugred_api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Specification {

    public static final String getUser = "/getuser";
    public static final String doRegester = "/doregister";
    public static final String createTask = "/createtask";
    public static final String addTaskToCron = "/addtaskincron";
    public static final String createCompany = "/createcompany";
    public static final String addAvatar = "/addavatar";
    public static final String deleteUser = "/deleteuser";
    public static final String search = "/magicsearch";
    public static final String deleteTask = "/deletetask";
    public static final File myFile = new File("C:\\Users\\Home\\Desktop\\Evgeniy\\JavaProjects\\avatar.jpg");
    static CreateResponse response = new CreateResponse();

    public RequestSpecification setupSpecification() {

        return given()
                .baseUri("http://users.bugred.ru/tasks/rest")
                .contentType(ContentType.JSON);
    }
}
