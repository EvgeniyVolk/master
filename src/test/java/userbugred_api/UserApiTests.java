package userbugred_api;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

public class UserApiTests {
    public static final String baseURI = "http://users.bugred.ru/tasks/rest";

    @Test
    private void testUserRegistered() {

        Response response = given()
                .baseUri(baseURI)
                .param("email", "auto@test.email")
                .contentType(ContentType.JSON)
                .when()
                .get("/getuser")
                .then()
                .statusCode(200)
                .log().all()
                .assertThat()
                .body("email", equalTo("auto@test.email"))
                .extract().response();
    }

    @Test (priority = 2)
    private void createUser() {
        Map<String, String> request = new HashMap<>();

        request.put("name", "ApiUser1");
        request.put("email", "apitest1@rest.com");
        request.put("password", "passwordapi1");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(baseURI + "/doregister")
                        .then().log().all()
                        .extract().response();
        assertEquals(200, response.getStatusCode());
    }

    @Test (priority = 3)
    private void createTask() {
        Map<String, String> request = new HashMap<>();
        request.put("task_title", "Test creation a new task");
        request.put("task_description", "Test creation a new task");
        request.put("email_owner", "auto@test.email");
        request.put("email_assign", "apitest1@rest.com");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(baseURI + "/createtask")
                        .then().log().all()
                        .extract().response();

        assertEquals(200, response.getStatusCode());
    }

    @Test (priority = 4)
    private void addTaskToCron() {
        Map<String, String> request = new HashMap<>();

        request.put("task_id", "175");
        request.put("email_owner", "auto@test.email");
        request.put("hours", "16");
        request.put("minutes", "20");
        request.put("month", "11");
        request.put("days", "22");
        request.put("day_weeks", "2");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(baseURI + "/addtaskincron")
                .then().log().all()
                .assertThat()
                .body("type", equalTo("success"))
                .extract().response();

        assertEquals(200, response.getStatusCode());
    }
}
