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
        createRequest.setTask_id(String.valueOf(Specification.response.getId_task()));

        Specification.response = given()
                .spec(specification.setupSpecification())
                .body(createRequest)
                .when().post(Specification.deleteTask)
                .then().log().all()
                .extract().body().as(CreateResponse.class);
    }
}
