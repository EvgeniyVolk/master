package userbugred_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CreateResponse {

    String type;
    Integer id_task;
    String message;
    String name;
    String email;
    String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMessage() {
        return message;
    }

    public Integer getId_task() {
        return id_task;
    }

    public String getType() {
        return type;
    }
}
