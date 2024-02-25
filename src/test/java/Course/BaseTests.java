package Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BaseTests {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String baseUrl = "http://localhost:8080";
    @BeforeEach
    public void setup()
    {

//        FirefoxOptions ffOptions = new FirefoxOptions();
//       // ffOptions.addArguments("-headless");
//        driver = new FirefoxDriver(ffOptions);

//        EdgeOptions ieOptions = new EdgeOptions();
//        ieOptions.addArguments("--headless=new");
//        driver = new EdgeDriver(ieOptions);

        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);

        // implicitWait shouldn't be used when webDriverWait is used
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
        // Duration pageLoadTimeout = driver.manage().timeouts().getPageLoadTimeout();
    }
    @AfterEach
    public void teardown()
    {
        driver.quit();
    }
    public WebElement Find(By selector)
    {
        return driver.findElement(selector);
    }
    public List<WebElement> FindMany(By selector)
    {
        return driver.findElements(selector);
    }
    public void SwitchFrame(By selector)
    {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(selector));
    }
}
