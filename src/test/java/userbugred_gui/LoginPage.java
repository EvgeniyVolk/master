package userbugred_gui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    //Page Locators
    private static SelenideElement loginBtn = $x("//a[@href='/user/login/index.html']");

    @Test
    public void openLoginPage() { loginBtn.shouldBe(Condition.enabled).click(); }
}
