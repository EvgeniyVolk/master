package userbugred_gui;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;


public class UserRegistration {
    WebDriver driver = new ChromeDriver();
    String userEmail;
    String baseUri = "http://users.bugred.ru/";

    @BeforeTest
    private void setUp() {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        inputName.sendKeys("Злобный босс");

        WebElement inputEmail = driver.findElement(By.name("email"));
        inputEmail.sendKeys("manager@mail.ru");
        userEmail = inputEmail.getAttribute("value");

        WebElement inputPassword = driver.findElement(By.xpath("//form[@action='/user/register/index.html'] //input[@name='password']"));
        inputPassword.sendKeys("1");

        WebElement registerBtn = driver.findElement(By.name("act_register_now"));
        registerBtn.click();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        softAssert.assertEquals(driver.getCurrentUrl(), baseUri, "User registration failed! Probably such user already exists");

        softAssert.assertAll();
    }

    @Test (priority = 4)
    private void loginUser() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        WebElement inputLogin = driver.findElement(By.xpath("//input[@name='login']"));
        inputLogin.sendKeys("manager@mail.ru");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("1");

        WebElement loginButton = driver.findElement(By.xpath("//*[@value='Авторизоваться']"));
        loginButton.click();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        softAssert.assertTrue(driver.getCurrentUrl().equals(baseUri), "User login failed!");

        softAssert.assertAll();
    }

    @Test(priority = 5)
    private void checkAvatar() {
        SoftAssert softAssert = new SoftAssert();
        try {
            WebElement search = driver.findElement(By.xpath("//input[@placeholder='Введите email или имя']"));
            search.sendKeys("apitest3");

            WebElement searchBtn = driver.findElement(By.xpath("//button[@class='btn btn-submit']"));
            searchBtn.click();

            WebElement userEmail = driver.findElement(By.xpath("//tbody[@class='ajax_load_row'] //*[contains(text(), 'apitest3@rest.com')]"));

            if(userEmail.isDisplayed()) {
                WebElement viewBtn = driver.findElement(By.xpath("//tbody[@class=\"ajax_load_row\"] //*[contains(text(), \"Посмотреть\")]"));
                viewBtn.click();
            }

            WebElement avatar = driver.findElement(By.xpath("//div[@class='col-md-4 center'] //img[@src='/tmp/files/avatar.jpg']"));

        } catch (NoSuchElementException e) {
            softAssert.assertTrue(e.getMessage().isEmpty());
            e.printStackTrace();
            softAssert.assertAll();
        }
    }
}
