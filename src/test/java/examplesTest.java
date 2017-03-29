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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Alexandor on 02.01.2017.
 */
public class examplesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\st\\Desktop\\Vlada\\drivers\\chromedriver.exe"); //change path in IT Step room
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
        String successfulMessageText = "You logged into a secure area!";

        driver.findElement(By.linkText("Form Authentication")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        WebElement loginField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        driver.findElement(By.className("radius")).click();

        WebElement messagePane = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("flash")));

        assertTrue(messagePane.isDisplayed(), "Warning successful pane is not displayed");
        assertTrue(messagePane.getText().contains(successfulMessageText), "Warning message is not displayed");

        WebElement logoutButton = driver.findElement(By.className("button"));
        logoutButton.click();

        assertTrue(driver.findElement(By.id("username")).isDisplayed(), "Login field is not displayed");
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
    public void alertSecondTest() {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".example > ul > li:nth-child(2) > button")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.dismiss();
    }

    @Test
    public void alertThirdTest() {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".example > ul > li:last-child > button")).click();
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

        int windowsCount =  driver.getWindowHandles().size();
        System.out.println("Initial windows count: " + windowsCount);

        driver.findElement(By.cssSelector(".example>a")).click();
        Thread.sleep(5000);
        int updatedWindowsCount =  driver.getWindowHandles().size();
        System.out.println("Updated windows count: " + updatedWindowsCount);

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        String textFromNewWindow = driver.findElement(By.cssSelector(".example > h3")).getText();
        System.out.println(textFromNewWindow);
        //driver.switchTo().defaultContent();
        //driver.findElement(By.cssSelector(".example>a")).click();

        //driver.navigate().back(); this methods can be use in tests
    }

    @Test
    public void frameTest() {
        driver.findElement(By.linkText("Frames")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Nested Frames")).click();

        List<WebElement> frames = driver.findElements(By.tagName("frame"));

        driver.switchTo().frame(frames.get(0)); //or use search frameset by tag name

        driver.switchTo().frame(driver.findElement(By.name("frame-left")));
        System.out.println("Get page source from left frame: " + driver.getPageSource());

        driver.switchTo().parentFrame();
        //then you can switch to other frames

        driver.switchTo().defaultContent();
        driver.switchTo().frame(frames.get(1)); //driver.switchTo().frame(driver.findElement(By.name("frame-bottom")));
        String textFromBottomFrame = driver.findElement(By.tagName("body")).getText();
    }

    @Test
    public void iFrameTest() throws InterruptedException {
        driver.findElement(By.linkText("Frames")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.findElement(By.linkText("iFrame")).click();

        WebElement iFrame = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iFrame);

        Actions action = new Actions(driver);
        //1) this two steps send data to form ��� ����� �� ������, ���� ���� ����� ��� �� ������ � ��� �� ��� ���������� �����, ���� ������� ������������ �����
       action.sendKeys(driver.findElement(By.cssSelector("html > body > p")), "text for delete");
       action.build().perform();
//      2) after previous steps form is active and we can delete clear it and send new text


        action.keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE).build().perform();
        action.sendKeys(driver.findElement(By.cssSelector("html > body > p")), "text for delete");
        action.build().perform();
        Thread.sleep(5000);

        //3) ��� ������� ����� ������������ ������ 1)
        //action.moveToElement(driver.findElement(By.cssSelector("html > body > p"))).click().perform();

        //4) this line clear form and input text at the same time
     //   ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = 'hiiiiiii'", driver.findElement(By.cssSelector("html > body > p")));
    }

    @Test
    public void hoverTest() {
        driver.findElement(By.linkText("Hovers")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        WebElement element = driver.findElement(By.cssSelector(".example > .figure:nth-child(3) > img"));

        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();

        assertTrue(driver.findElement(By.cssSelector(".example > .figure:nth-child(3)>.figcaption > h5")).isDisplayed());
        String textFromProfile = driver.findElement(By.cssSelector(".example > .figure:nth-child(3)>.figcaption > h5")).getText();
        System.out.println(textFromProfile);
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

        option.selectByValue("2");

        Thread.sleep(5000);
    }

    @Test
    public void dynamicLoadingFirstTest() {
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.findElement(By.linkText("Example 1: Element on page that is hidden")).click();

        WebElement element = driver.findElement(By.id("finish"));
        boolean isNotDisplayed = element.isDisplayed();
        System.out.println(isNotDisplayed);

        driver.findElement(By.cssSelector("#start>button")).click();
        WebElement loadingBar = driver.findElement(By.id("loading"));

        WebDriverWait waiter = new WebDriverWait(driver, 30);
        waiter.until(ExpectedConditions.attributeToBe(loadingBar, "style", "display: none;"));

        boolean isDisplayed = element.isDisplayed();
        System.out.println(isDisplayed);
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
    public void horizontalSliderTest() throws InterruptedException {
        driver.findElement(By.linkText("Horizontal Slider")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        WebElement slider = driver.findElement(By.cssSelector(".sliderContainer > input"));

        Actions move = new Actions(driver);
        move.dragAndDropBy(slider, 30, 0).build().perform();

        Thread.sleep(5000);
    }

    @Test
    public void javascriptTest() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String title = (String)js.executeScript("return document.title");
        long links = (Long) js.executeScript("var links = document.getElementsByTagName('a'); return links.length");
        System.out.println(title);
        System.out.println("Count links: " + links);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
