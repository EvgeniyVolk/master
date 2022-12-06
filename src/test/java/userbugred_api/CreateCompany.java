package userbugred_api;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class CreateCompany {
    @Test
    private void createCompany() {
        Specification specification = new Specification();
        ArrayList<String> users = new ArrayList<>();

        users.add("apitest2@rest.com");
        users.add("apitest3@rest.com");

        CreateRequest request = new CreateRequest();

        request.setCompany_name("API Rest Company");
        request.setCompany_type("ОАО");
        request.setEmail_owner("apitest1@rest.com");
        request.setCompany_users(users);


        Response response = given()
                .spec(specification.setupSpecification())
                .body(request)
                .when().post(Specification.createCompany)
                .then().log().all()
                .assertThat()
                .body("company.name", equalTo("API Rest Company"))
                .extract().response();

        assertEquals(200, response.getStatusCode());
    }
}
