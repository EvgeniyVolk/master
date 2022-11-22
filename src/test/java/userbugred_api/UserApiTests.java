package userbugred_api;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class UserApiTests {
    RequestSpecification requestSpecification;

    @BeforeClass
    public void setupRequestSpecefication() {

        requestSpecification = given()
                .baseUri("http://users.bugred.ru/tasks/rest")
                .contentType(ContentType.JSON);
    }

    @Test
    private void testUserRegistered() {

        Response response = given()
                .spec(requestSpecification)
                .param("email", "manager@mail.ru")
                .when().get("/getuser")
                .then().log().all()
                .assertThat()
                .body("email", equalTo("manager@mail.ru"))
                .extract().response();

        assertEquals(200, response.getStatusCode());
    }

    @Test (priority = 2)
    private void createUser() {
        int users = 2;

        for(Integer i=1; i<=users; i++) {

            Map<String, String> request = new HashMap<>();
            request.put("name", "ApiUser" + i.toString());
            request.put("email", "apitest" + i.toString() + "@rest.com");
            request.put("password", "passwordapi" + i.toString());

            Response response = given()
                    .spec(requestSpecification)
                    .body(request)
                    .when().post("/doregister")
                    .then().log().all()
                    .extract().response();

            assertEquals(200, response.getStatusCode());
        }
    }

    @Test (priority = 3)
    private void createTask() {

        Map<String, String> request = new HashMap<>();
        request.put("task_title", "Simple sample task");
        request.put("task_description", "Test creation a new task");
        request.put("email_owner", "apitest1@rest.com");
        request.put("email_assign", "apitest2@rest.com");

        Response response = given()
                .spec(requestSpecification)
                .body(request)
                .when().post("/createtask")
                .then().log().all()
                .extract().response();

        assertEquals(200, response.getStatusCode());
    }

    @Test (priority = 4)
    private void addTaskToCron() {

        Map<String, String> request = new HashMap<>();

        request.put("task_id", "55");
        request.put("email_owner", "apitest1@rest.com");
        request.put("hours", "16");
        request.put("minutes", "20");
        request.put("month", "11");
        request.put("days", "23");
        request.put("day_weeks", "3");

        Response response = given()
                .spec(requestSpecification)
                .body(request)
                .when().post("/addtaskincron")
                .then().log().all()
                .assertThat()
                .body("type", equalTo("success"))
                .extract().response();

        assertEquals(200, response.getStatusCode());
    }
}
