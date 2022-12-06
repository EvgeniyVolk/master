package userbugred_api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DoRegister {
    @Test
    private void doRegister() {
        Specification specification = new Specification();
        CreateRequest request = new CreateRequest();
        int users = 3;

        for(Integer i=1; i<=users; i++) {

//            Map<String, String> request = new HashMap<>();
            request.setName("ApiUser" + i.toString());
            request.setEmail("apitest" + i.toString() + "@rest.com");
            request.setPassword("passwordapi" + i.toString());

            CreateResponse response = given()
                    .spec(specification.setupSpecification())
                    .body(request)
                    .when().post(Specification.doRegester)
                    .then().log().all()
                    .assertThat()
                    .extract().body().as(CreateResponse.class);
        }
    }
}
