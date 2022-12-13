package userbugred_gui;

import com.codeborne.selenide.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class UserAuthorization {

    //Page Locators
    private static final SelenideElement inputName = $(byName("name"));
    private static final SelenideElement inputEmail = $(byName("email"));
    private static final SelenideElement inputPassword = $x("//form[@action='/user/register/index.html'] //input[@name='password']");
    private static final SelenideElement registerBtn = $(byName("act_register_now"));
    private static final SelenideElement registrationStatus = $x("//form[@action='/user/register/index.html']");
    private static final SelenideElement inputUserEmail = $x("//form[@action='/user/login/index.html'] //input[@name='login']");
    private static final SelenideElement inputUserPassword = $(byName("password"));
    private static final SelenideElement loginBtn = $x("//*[@value='Авторизоваться']");
    private static final SelenideElement userLink = $x("//li[@id='fat-menu']");

    //Existing User
    private static final String existingUserName = "Злобный босс";
    private static final String existingUserEmail = "manager@mail.ru";
    private static final String existingUserPassword = "1";

    @Test (priority = 1)
    public void registerAsExistingUser() {

        inputName.should(Condition.exist).sendKeys(existingUserName);
        inputEmail.should(Condition.exist).sendKeys(existingUserEmail);
        inputPassword.should(Condition.exist).sendKeys(existingUserPassword);
        registerBtn.shouldBe(Condition.enabled).click();
        registrationStatus.should(Condition.have(Condition.text("Занят ")));
    }

    @Test (priority = 2)
    public void loginAsRegisteredUser() {

        inputUserEmail.should(Condition.exist).sendKeys(existingUserEmail);
        inputUserPassword.should(Condition.exist).sendKeys(existingUserPassword);
        loginBtn.shouldBe(Condition.enabled).click();
        userLink.should(Condition.exist).should(Condition.have(Condition.text(existingUserName)));
    }
}
