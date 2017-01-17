import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by AlexanderSavenok on 1/17/2017.
 */
public class ScreenshotTest {

    WebDriver driver;

    @Test
    public void testmail() throws InterruptedException, IOException {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\WEBDrivers\\FireFox\\geckodriver.exe");
        driver = new FirefoxDriver();

        driver.get("https://mail.tut.by");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement user = driver.findElement(By.id("Username"));
        user.sendKeys("alexander08082016");
        WebElement pass = driver.findElement(By.id("Password"));
        pass.sendKeys("Newfield1");
        pass.submit();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement new_mess = driver.findElement(By.cssSelector(".b-toolbar__block_chevron a:nth-child(2) > .b-toolbar__item__label"));
        new_mess.click();

        WebElement set_email = driver.findElement(By.cssSelector(".js-compose-mail-input_to input[type='text']"));
        set_email.sendKeys("alexander08082016");
    }

    //add this method at the separate class
    public void captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/" + screenshotName + ".png"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()) {
            captureScreenshot(driver, result.getTestClass().getName() + "-" + result.getMethod().getMethodName() + " " + new Date().toString().replaceAll(" ", "").replaceAll(":", ""));
        }
        System.out.println("Execution time: " + (result.getEndMillis() - result.getStartMillis()));
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        driver.quit();
    }

}
