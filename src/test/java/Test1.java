import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

/**
 * Created by Alexandor on 28.12.2016.
 */
public class Test1 {

    @Test
    public void firstTest() throws InterruptedException {
        String s = System.getProperty("browser");
        System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        String s1 = System.getProperty("browser");
        WebDriver driver = new ChromeDriver();
        String s2 = System.getProperty("browser");
        driver.get("http://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        Assert.assertEquals("The Internet", driver.getTitle());
        driver.quit();

    }

}
