package userbugred_api;

import java.util.ArrayList;

public class CreateRequest {
    public String company_name;
    public String company_type;
    public String email_owner;
    public ArrayList<String> company_users;

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
}
