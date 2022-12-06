package userbugred_api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class DeleteResources {

    RequestSpecification requestSpecification;

    @BeforeTest
    public void setupRequestSpecification() {

        requestSpecification = given()
                .baseUri("http://users.bugred.ru/tasks/rest")
                .contentType(ContentType.JSON);
    }

    @Test
    private void deleteUser() {
        int users = 3;

        for (Integer i = 1; i <= users; i++) {

            Map<String, String> request = new HashMap<>();
            request.put("name", "ApiUser" + i.toString());
            request.put("email", "apitest" + i.toString() + "@rest.com");
            request.put("password", "passwordapi" + i.toString());

            Response response = given()
                    .spec(requestSpecification)
                    .body(request)
                    .when().post(Endpoints.deleteUser)
                    .then().log().all()
                    .assertThat()
                    .extract().response();

            assertEquals(200, response.getStatusCode());
//            assertEquals(response.getBody().asString(), containsString("Пользователь с таким email не найден!") );
//            assertEquals("message", "Пользователь с таким email не найден!");
        }
    }

    @Test
    private void deleteTask() {
        CreateResponse createResponse =  new CreateResponse();
        CreateRequest createRequest = new CreateRequest();

        createRequest.setEmail_owner("apitest1@rest.com");
        createRequest.setCompany_name("API Rest Company");
        createRequest.setTask_id("47");

        Response response = given()
                .spec(requestSpecification)
                .body(createRequest)
                .when().put(Endpoints.deleteTask)
                .then().log().all()
                .extract().response();
    }
}
