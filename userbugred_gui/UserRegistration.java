package userbugred_gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;
public class UserRegistration {
    protected static String userEmail;
    WebDriver driver = new ChromeDriver();
    static final String baseUri = "http://users.bugred.ru/";

    @BeforeTest
    private void setUp() {
        driver.manage().window().maximize();
        driver.get(baseUri);
    }

    @Test (priority = 1)
    private void openLoginPage() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        WebElement loginBtn = driver.findElement(By.xpath("//a[@href=\"/user/login/index.html\"]"));
        softAssert.assertTrue(loginBtn.isDisplayed(), "WebElement 'loginBtn' is not found");
        loginBtn.click();

        softAssert.assertAll();
    }

    @Test (priority = 2)
    private void registerNewUser() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        WebElement inputName = driver.findElement(By.name("name"));
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword = driver.findElement(By.xpath("//form[@action='/user/register/index.html'] //input[@name='password']"));
        WebElement registerBtn = driver.findElement(By.name("act_register_now"));

        inputName.sendKeys("Злобный босс");
        inputEmail.sendKeys("manager@mail.ru");
        userEmail = inputEmail.getAttribute("value");
        inputPassword.sendKeys("1");
        registerBtn.click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        softAssert.assertEquals(driver.getCurrentUrl(), baseUri, "User registration failed! Probably such user already exists");

        softAssert.assertAll();
    }

    @Test (priority = 4)
    private void loginUser() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        WebElement inputLogin = driver.findElement(By.xpath("//input[@name='login']"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@value='Авторизоваться']"));

        inputLogin.sendKeys("manager@mail.ru");
        password.sendKeys("1");
        loginButton.click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        softAssert.assertTrue(driver.getCurrentUrl().equals(baseUri), "User login failed!");

        softAssert.assertAll();
    }
}
