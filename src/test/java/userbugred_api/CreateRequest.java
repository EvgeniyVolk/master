package userbugred_api;

import java.io.File;
import java.util.ArrayList;

public class CreateRequest {
    public String company_name;
    public String company_type;
    public String email_owner;
    public ArrayList<String> company_users;

    public String task_title;
    public String task_description;
    public String email_assign;

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public void setEmail_owner(String email_owner) {
        this.email_owner = email_owner;
    }

    public void setCompany_users(ArrayList<String> company_users) {
        this.company_users = company_users;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public void setEmail_assign(String email_assign) {
        this.email_assign = email_assign;
    }


}
