import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

/**
 * Created by AlexanderSavenok on 1/10/2017.
 */
public class htmlUnitTest {

    @Test
    public void dropDowmTest() {


        //System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\WEBDrivers\\FireFox\\geckodriver.exe"); //change path in the IBA room
        WebDriver driver = new HtmlUnitDriver();

        long startTime = System.currentTimeMillis();

        driver.get("http://newtours.demoaut.com/mercuryregister.php?osCsid=d5c21a9bed7d26024b2d62418a0947a4");
        //driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement dropdown = driver.findElement(By.cssSelector("form>table>tbody>tr>td>select"));

        Select select = new Select(dropdown);

        for (int i = 0; i < select.getOptions().size(); ++i) {
            select.selectByIndex(i);
        }

        driver.quit();

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);

    }

}
