import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

/**
 * Created by Alexandor on 24.01.2017.
 */
public class MyListener implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] captureScreenshot() {
        TakesScreenshot takesScreenshot = (TakesScreenshot) TestBase.getDriver();
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Info", type = "text/plain")
    private String getInfo() {
        return "Hello world!\nYour test failed!";
    }
}
