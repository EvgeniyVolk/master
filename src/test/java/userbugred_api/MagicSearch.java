package userbugred_api;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MagicSearch {

    @Test
    private void magicSearch() {

        Specification specification = new Specification();

        Response response = given()
                .param("query", "ApiUser")
                .spec(specification.setupSpecification())
                .when().get(Specification.search)
                .then().log().all()
                .assertThat()
                .extract().response();
    }
}
