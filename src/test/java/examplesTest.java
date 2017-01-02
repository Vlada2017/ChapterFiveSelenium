import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

/**
 * Created by Alexandor on 02.01.2017.
 */
public class examplesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe"); //change path in the IBA room
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void alertFirstTest() throws InterruptedException {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".example>ul>li:first-child>button")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
    }

    @Test
    public void alertSecondTest() throws InterruptedException {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".example>ul>li:nth-child(2)>button")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.dismiss();
    }

    @Test
    public void alertThirdTest() throws InterruptedException {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".example>ul>li:last-child>button")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Hello");
        alert.accept();

        assertTrue(driver.findElement(By.id("result")).getText().contains("Hello"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
