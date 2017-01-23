import org.openqa.selenium.WebDriver;

/**
 * Created by Alexandor on 24.01.2017.
 */
public class TestBase {
    protected static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }
}
