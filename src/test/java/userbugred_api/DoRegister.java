package userbugred_api;

import db_connection.InsertUserQuery;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class DoRegister {

    InsertUserQuery insert = new InsertUserQuery();

    @Test
    private void doRegister() throws SQLException {
        Specification specification = new Specification();
        CreateRequest request = new CreateRequest();
        int users = 3;

        for(Integer i=1; i<=users; i++) {

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

             if(response.getName() != null) {
                 insert.insertNewRecord(response.getName(), response.getEmail(), "unknown");
             }
        }
    }
}
