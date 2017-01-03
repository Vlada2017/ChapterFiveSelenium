import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\WEBDrivers\\Chrome\\chromedriver.exe"); //change path in the IBA room
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //other driver methods, cookie page resourses and other

    @Test
    public void loginTest() {
        String login = "tomsmith";
        String password = "SuperSecretPassword!";

        driver.findElement(By.linkText("Form Authentication")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        WebElement loginField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        driver.findElement(By.className("radius")).click();

        assertTrue(driver.findElement(By.id("flash")).isDisplayed());
        //add logout
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

    @Test
    public void basicAuthenticationTest() throws InterruptedException {
        String URL = "http://admin:admin@the-internet.herokuapp.com/basic_auth";
        driver.navigate().to(URL);
    }

    @Test
    public void openNewWindowTest() throws InterruptedException {
        driver.findElement(By.linkText("Multiple Windows")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        int windows =  driver.getWindowHandles().size();

        driver.findElement(By.cssSelector(".example>a")).click();
        Thread.sleep(5000);
        int updatedWindows =  driver.getWindowHandles().size();



        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        String textFromNewWindow = driver.findElement(By.cssSelector(".example>h3")).getText();
        //driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".example>a")).click();

        //driver.navigate().back(); this methods can be use in tests
    }

    @Test
    public void frameTest() {
        driver.findElement(By.linkText("Frames")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.findElement(By.linkText("Nested Frames")).click();

        List<WebElement> frames = driver.findElements(By.tagName("frame"));

        //driver.switchTo().frame(driver.findElement(By.name("frame-bottom")));
        driver.switchTo().frame(frames.get(1));
        String text = driver.getPageSource();
        String textFromBottomFrame = driver.findElement(By.tagName("body")).getText();

                /*
        driver.switchTo().frame(frames.get(0));
        WebElement leftFrame = driver.findElement(By.name("frame-left"));
        driver.switchTo().frame(leftFrame);
        String textFromLeftFrame = driver.findElement(By.cssSelector("html > body")).getText();
        */
    }

    @Test
    public void hoverTest() {
        driver.findElement(By.linkText("Hovers")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        WebElement element = driver.findElement(By.cssSelector(".example > .figure:nth-child(3)>img"));

        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();

        assertTrue(driver.findElement(By.cssSelector(".example > .figure:nth-child(3)>.figcaption > h5")).isDisplayed());
        String text = driver.findElement(By.cssSelector(".example > .figure:nth-child(3)>.figcaption > h5")).getText();
    }

    @Test
    public void contextMenuTest() throws InterruptedException {
        driver.findElement(By.linkText("Context Menu")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        action.contextClick(driver.findElement(By.id("hot-spot"))).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();

        Thread.sleep(5000);
    }

    @Test
    public void dropdownTest() throws InterruptedException {
        driver.findElement(By.linkText("Dropdown")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        Select option = new Select(driver.findElement(By.id("dropdown")));

        option.selectByVisibleText("Option 1");

        Thread.sleep(5000);
    }

    @Test
    public void dynamicLoadingFirstTest() {
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.findElement(By.linkText("Example 1: Element on page that is hidden")).click();

        WebElement element = driver.findElement(By.id("finish"));
        boolean isNotDisplayed = element.isDisplayed();

        driver.findElement(By.cssSelector("#start>button")).click();
        WebElement loadingBar = driver.findElement(By.id("loading"));

        WebDriverWait waiter = new WebDriverWait(driver, 30);
        waiter.until(ExpectedConditions.attributeToBe(loadingBar, "style", "display: none;"));

        boolean isDisplayed = element.isDisplayed();
    }

    @Test
    public void dynamicLoadingSecondTest() {
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.findElement(By.linkText("Example 2: Element rendered after the fact")).click();

        driver.findElement(By.cssSelector("#start>button")).click();

        WebDriverWait waiter = new WebDriverWait(driver, 30);
        waiter.until(ExpectedConditions.not(ExpectedConditions.invisibilityOfElementLocated(By.id("loading"))));

        WebElement element = driver.findElement(By.id("finish"));
        boolean isDisplayed = element.isDisplayed();
    }

    @Test
    public void horizontalSliderTest() {
        driver.findElement(By.linkText("Horizontal Slider")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        WebElement slider = driver.findElement(By.cssSelector(".sliderContainer>input"));

        Actions move = new Actions(driver);
        move.dragAndDropBy(slider, 30, 0).build().perform();
    }

    @Test
    public void javascriptTest() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String title = (String)js.executeScript("return document.title");
        long links = (Long) js.executeScript("var links = document.getElementsByTagName('a'); return links.length");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
