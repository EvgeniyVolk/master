package userbugred_gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import userbugred_api.UserApiTests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;


public class UserRegistration {
    protected static String userEmail;
    WebDriver driver = new ChromeDriver();
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    private void setUp() {
        String baseUri = "http://users.bugred.ru";
        driver.manage().window().maximize();
        driver.get(baseUri);
    }

    @Test
    private void openLoginPage() throws InterruptedException {
        WebElement loginBtn = driver.findElement(By.xpath("//a[@href=\"/user/login/index.html\"]"));
        softAssert.assertTrue(loginBtn.isDisplayed(), "WebElement 'loginBtn' is not found");
        loginBtn.click();

        softAssert.assertAll();
    }

    @Test
    private void registerNewUser() throws InterruptedException {
        String expectedUrl = "http://users.bugred.ru/";

        WebElement inputName = driver.findElement(By.name("name"));
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword = driver.findElement(By.xpath("//form[@action='/user/register/index.html'] //input[@name='password']"));
        WebElement registerBtn = driver.findElement(By.name("act_register_now"));

        inputName.sendKeys("AutoTestName");
        inputEmail.sendKeys("auto@test.email");
        userEmail = inputEmail.getAttribute("value");
        inputPassword.sendKeys("PasswordTestAuto");
        registerBtn.click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl, "User registration failed! Probably such user already exists");
        softAssert.assertAll();
    }
}
