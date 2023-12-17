import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class BaseTests {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String baseUrl = "http://localhost:8080";
    @BeforeEach
    public void setup()
    {
//        FirefoxOptions ffOptions = new FirefoxOptions();
//        ffOptions.addArguments("-headless");
//        driver = new FirefoxDriver(ffOptions);
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless=new");
//        driver = new ChromeDriver(chromeOptions);

        EdgeOptions ieOptions = new EdgeOptions();
        ieOptions.addArguments("--headless=new");
        driver = new EdgeDriver(ieOptions);

        // implicitWait shouldn't be used when webDriverWait is used
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        /*
         Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
         Duration pageLoadTimeout = driver.manage().timeouts().getPageLoadTimeout();
        */
    }
    @AfterEach
    public void teardown()
    {
        driver.quit();
    }

}
