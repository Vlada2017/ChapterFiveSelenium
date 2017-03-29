import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alexandor on 02.01.2017.
 */
public class CalculatorTest {

    public static final By RESULT_FIELD = By.id("calc_display");
    public static final By ESCAPE = By.id("btn_esc");
    public static final By ONE = By.id("btn_1");
    public static final By TWO = By.id("btn_2");
    public static final By THREE = By.id("btn_3");
    public static final By FOUR = By.id("btn_4");
    public static final By FIVE = By.id("btn_5");
    public static final By SIX = By.id("btn_6");
    public static final By SEVEN = By.id("btn_7");
    public static final By EIGHT = By.id("btn_8");
    public static final By NINE = By.id("btn_9");
    public static final By ZERO = By.id("btn_0");
    public static final By PLUS = By.id("btn_plus");
    public static final By MINUS = By.id("btn_minus");
    public static final By MULT = By.id("btn_mult");
    public static final By DIV = By.id("btn_div");
    public static final By ENTER = By.id("btn_enter");

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe"); //change path in IT Step
        driver = new FirefoxDriver();
        driver.get("http://calc.by/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void calculateSumTest() {
        driver.switchTo().frame(driver.findElement(By.id("standardCalc")));

        WebElement element = driver.findElement(RESULT_FIELD);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        WebElement result = driver.findElement(RESULT_FIELD);
        performOperation(driver, SIX, FOUR, PLUS);

        Assert.assertEquals("10", result.getAttribute("value"), "Result is not valid");
    }

    @Test
    public void calculateMinusTest(){
        driver.switchTo().frame(driver.findElement(By.id("standardCalc")));

        WebElement element = driver.findElement(RESULT_FIELD);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        WebElement result = driver.findElement(RESULT_FIELD);
        performOperation(driver, NINE, FOUR, MINUS);

        Assert.assertEquals("5", result.getAttribute("value"), "Result is not valid");

        WebDriverWait waiter = new WebDriverWait(driver, 10);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("")));
    }

    public void performOperation(WebDriver driver, By firstElement, By secondElement, By operation) {
        driver.findElement(firstElement).click();
        driver.findElement(operation).click();
        driver.findElement(secondElement).click();
        driver.findElement(ENTER).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
