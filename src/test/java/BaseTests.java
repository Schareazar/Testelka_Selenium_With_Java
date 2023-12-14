import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class BaseTests {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String baseUrl = "http://localhost:8080";
    @BeforeEach
    public void setup()
    {
        driver = new ChromeDriver();
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
