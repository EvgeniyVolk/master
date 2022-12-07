package userbugred_api;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GetUser {

    @Test
    private void getUser() {
        Specification specification = new Specification();

        Response response = given()
                .spec(specification.setupSpecification())
                .param("email", "manager@mail.ru")
                .when().get(Specification.getUser)
                .then().log().all()
                .assertThat()
                .body("email", equalTo("manager@mail.ru"))
                .extract().response();

        assertEquals(200, response.getStatusCode());
    }
}
