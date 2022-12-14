package userbugred_api;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DeleteUser {

    @Test
    private void deleteUser() {
        int users = 3;
        Specification specification = new Specification();

        for (Integer i = 1; i <= users; i++) {

            Map<String, String> request = new HashMap<>();
            request.put("name", "ApiUser" + i.toString());
            request.put("email", "apitest" + i.toString() + "@rest.com");
            request.put("password", "passwordapi" + i.toString());

            Response response = given()
                    .spec(specification.setupSpecification())
                    .body(request)
                    .when().post(Specification.deleteUser)
                    .then().log().all()
                    .assertThat()
                    .extract().response();

            assertEquals(200, response.getStatusCode());
        }
    }
}
