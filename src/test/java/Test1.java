import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alexandor on 28.12.2016.
 */
public class Test1 {

    //Starting with: input[id^='ctrl']
    //Ending with: input[id$='_userName']
    //Containing: Input[id*='userName']

    //element:first-child This will locate the first element under the element.
    //element:last-child  This will locate the last element under the element.
    //element:nth-child(2)  This will locate the second child element under the element.

    @Test
    public void firstTest() throws InterruptedException {
        String username = "dpialexanders@gmail.com";
        String password = "Password1*";

        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\WEBDrivers\\FireFox\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\WEBDrivers\\Chrome\\chromedriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://gmail.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.navigate().back(); //also refresh and forward. in this case may verify using url or title or elements on the page.

        WebElement usernameField = driver.findElement(By.id("Email"));
        usernameField.sendKeys(username);

        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();

        WebElement passwordField = driver.findElement(By.id("Passwd"));
        //WebElement passwordField = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("Passwd")));
        passwordField.sendKeys(password);

        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();

        Assert.assertEquals(driver.getTitle(), "Gmail");

        driver.quit();
    }

}
