import io.qameta.allure.Attachment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class BaseTest {


    public TravelocitySteps steps;
    public WebDriver driver;
    String browserName = System.getProperty("browser");

    @Rule
    public TestRule testWatcher = new TestWatcher() {

        public void finished(Description desc) {
            driver.quit();
        }

        @Override

        protected void failed(Throwable e, Description description) {
            super.failed(e, description);

           saveScreenshot();
        }
    };

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot() {
        return  ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);

    }


    @Before
    public void setUp(){


        WebDriverManager driverManager = new WebDriverManager(driver);
        driver = driverManager.getInstance(browserName);
        steps = new TravelocitySteps(driver);


    }


}
