package userbugred_api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateTask {
    @Test
    private void createTask() {
//        CreateResponse response = new CreateResponse();
        CreateRequest request = new CreateRequest();
        Specification specification = new Specification();

        request.setTask_title("Simple sample task");
        request.setTask_description("Test creation a new task");
        request.setEmail_owner("apitest1@rest.com");
        request.setEmail_assign("apitest3@rest.com");

        Specification.response = given()
                .spec(specification.setupSpecification())
                .body(request)
                .when().post(Specification.createTask)
                .then().log().all()
                .assertThat()
                .body("type", equalTo("success"))
                .extract().body().as(CreateResponse.class);
    }
}
