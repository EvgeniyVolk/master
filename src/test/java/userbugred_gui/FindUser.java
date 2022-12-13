package userbugred_gui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$x;

public class FindUser {

    //Page Locators
    private static final SelenideElement searchBar = $x("//input[@placeholder='Введите email или имя']");
    private static final SelenideElement searchBtn = $x("//button[@class='btn btn-submit']");
    private static final SelenideElement resultSection =$x("//tbody[@class='ajax_load_row']");

    private static final String keyWord = "ApiUser3";

    @Test
    public void searchByKeyWord() {
        searchBar.should(Condition.exist).sendKeys(keyWord);
        searchBtn.shouldBe(Condition.enabled).click();
        resultSection.should(Condition.have(Condition.text(keyWord)));
    }
}
