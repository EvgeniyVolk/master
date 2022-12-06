package userbugred_api;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddTaskInCron {
    @Test
    private void addTaskInCron() throws NullPointerException  {
        Specification specification = new Specification();
        CreateResponse createResponse = new CreateResponse();

        try {
            LocalDate localDate = LocalDate.now();
            LocalTime time = LocalTime.now();
            DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
            Map<String, String> request = new HashMap<>();

            String id;
            Integer taskId = createResponse.getId_task();
            if(taskId != null) { id = String.valueOf(taskId); } // if some integer got from response
            else { id = "54"; }  // defaulted task id
            int day = localDate.getDayOfMonth();
            int weekDay = dayOfWeek.getValue();
            int month = localDate.getMonthValue();
            int hour = time.getHour(); //does not work if hour is '0', because format should be '00'
            int minute = time.getMinute();
            if (minute + 15 <= 59) { minute += 15; }

            request.put("task_id", id);
            request.put("email_owner", "apitest1@rest.com");
            request.put("hours", String.valueOf(hour));
            request.put("minutes", String.valueOf(minute));
            request.put("month", String.valueOf(month));
            request.put("days", String.valueOf(day));
            request.put("day_weeks", String.valueOf(weekDay));

            Response response = given()
                    .spec(specification.setupSpecification())
                    .body(request)
                    .when().post(Specification.addTaskToCron)
                    .then().log().all()
                    .assertThat()
                    .body("type", equalTo("success"))
                    .extract().response();

        } catch (NullPointerException e) {
            System.out.println("Task Id is null");
        }
    }
}
