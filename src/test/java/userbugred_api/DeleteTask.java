package userbugred_api;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteTask {
    @Test
    private void deleteTask() {
        Specification specification = new Specification();

        CreateRequest createRequest = new CreateRequest();

        createRequest.setEmail_owner("apitest1@rest.com");
        createRequest.setCompany_name("API Rest Company");
        createRequest.setTask_id("47");

        Response response = given()
                .spec(specification.setupSpecification())
                .body(createRequest)
                .when().put(Specification.deleteTask)
                .then().log().all()
                .extract().response();
    }
}
