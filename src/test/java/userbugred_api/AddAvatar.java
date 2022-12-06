package userbugred_api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddAvatar {

    @Test
    private void addAvatar() {

        Response response = given()
                .baseUri("http://users.bugred.ru/tasks/rest")
                .param("email", "apitest3@rest.com")
                .contentType(ContentType.MULTIPART)
                .multiPart("avatar", Specification.myFile) //sending an avatar file
                .when().post(Specification.addAvatar)
                .then().log().all()
                .assertThat()
                .body("status", equalTo("ok"))
                .extract().response();
    }
}
