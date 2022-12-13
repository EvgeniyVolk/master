package userbugred_gui;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.open;

public class StartPage {

    @Test
    public void openSite() {

        open("http://users.bugred.ru/");
        holdBrowserOpen = true;
    }
}
