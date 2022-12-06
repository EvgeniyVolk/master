package userbugred_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import org.apache.commons.lang3.ObjectUtils;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.time.DayOfWeek;
import java.lang.*;

import static org.assertj.core.api.AbstractSoftAssertions.assertAll;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class UserApiTests {
    RequestSpecification requestSpecification;
    CreateResponse createResponse = new CreateResponse();
    SoftAssertions softAssertions = new SoftAssertions();

    @BeforeTest
    public void setupRequestSpecification() {

        requestSpecification = given()
                .baseUri("http://users.bugred.ru/tasks/rest")
                .contentType(ContentType.JSON);
    }

    @Test
    private void testUserRegistered() {

        Response response = given()
                .spec(requestSpecification)
                .param("email", "manager@mail.ru")
                .when().get(Endpoints.getUser)
                .then().log().all()
                .assertThat()
                .body("email", equalTo("manager@mail.ru"))
                .extract().response();

        assertEquals(200, response.getStatusCode());
    }

    @Test (priority = 2)
    private void createUser() {
        CreateRequest request = new CreateRequest();

        int users = 3;

        for(Integer i=1; i<=users; i++) {

//            Map<String, String> request = new HashMap<>();
            request.setName("ApiUser" + i.toString());
            request.setEmail("apitest" + i.toString() + "@rest.com");
            request.setPassword("passwordapi" + i.toString());

            CreateResponse response = given()
                    .spec(requestSpecification)
                    .body(request)
                    .when().post(Endpoints.doRegester)
                    .then().log().all()
                    .assertThat()
                    .extract().body().as(CreateResponse.class);
        }
    }

    @Test (priority = 3)
    private void createTask() {

        CreateRequest request = new CreateRequest();

        request.setTask_title("Simple sample task");
        request.setTask_description("Test creation a new task");
        request.setEmail_owner("apitest1@rest.com");
        request.setEmail_assign("apitest3@rest.com");

        createResponse = given()
                .spec(requestSpecification)
                .body(request)
                .when().post(Endpoints.createTask)
                .then().log().all()
                .assertThat()
                .body("type", equalTo("success"))
                .extract().body().as(CreateResponse.class);
    }

    @Test (priority = 4)
    private void addTaskToCron() throws NullPointerException  {
    try {
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.now();
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
        Map<String, String> request = new HashMap<>();

        String id;
        Integer taskId = createResponse.getId_task();
        if(taskId != null) { id = String.valueOf(taskId); } // if some integer got from response
        else { id = "88"; }  // defaulted task id
        int day = localDate.getDayOfMonth();
        int weekDay = dayOfWeek.getValue();
        int month = localDate.getMonthValue();
        int hour = time.getHour(); //does not work if hour is '0', because format should be '00'
        int minute = time.getMinute();
        if (minute + 15 <= 59) { minute += 15; }

        request.put("task_id", id);
        request.put("email_owner", "apitest1@rest.com");
        request.put("hours", String.valueOf(hour));
        request.put("minutes", String.valueOf(minute));
        request.put("month", String.valueOf(month));
        request.put("days", String.valueOf(day));
        request.put("day_weeks", String.valueOf(weekDay));

        Response response = given()
                .spec(requestSpecification)
                .body(request)
                .when().post(Endpoints.addTaskToCron)
                .then().log().all()
                .assertThat()
                .body("type", equalTo("success"))
                .extract().response();

        } catch (NullPointerException e) {
            System.out.println("Task Id is null");
        }
    }

    @Test (priority = 5)
    private void createCompany() {

        ArrayList<String> users = new ArrayList<>();

        users.add("apitest2@rest.com");
        users.add("apitest3@rest.com");

        CreateRequest request = new CreateRequest();

        request.setCompany_name("API Rest Company");
        request.setCompany_type("ОАО");
        request.setEmail_owner("apitest1@rest.com");
        request.setCompany_users(users);


        Response response = given()
                .spec(requestSpecification)
                .body(request)
                .when().post(Endpoints.createCompany)
                .then().log().all()
                .assertThat()
                .body("company.name", equalTo("API Rest Company"))
                .extract().response();

        assertEquals(200, response.getStatusCode());
    }

    @Test (priority = 6)
    private void addAvatar() {
        File myFile = new File("C:\\Users\\Home\\Desktop\\Evgeniy\\JavaProjects\\avatar.jpg");

        Response response = given()
                .baseUri("http://users.bugred.ru/tasks/rest")
                .param("email", "apitest3@rest.com")
                .contentType(ContentType.MULTIPART)
                .multiPart("avatar", myFile) //sending an avatar file
                .when().post(Endpoints.addAvatar)
                .then().log().all()
                .assertThat()
                .body("status", equalTo("ok"))
                .extract().response();
    }

    @Test (priority = 7)
    private void magicSearch() {

        Response response = given()
                .param("query", "Api")
                .spec(requestSpecification)
                .when().get(Endpoints.search)
                .then().log().all()
                .assertThat()
                .extract().response();
    }
}
