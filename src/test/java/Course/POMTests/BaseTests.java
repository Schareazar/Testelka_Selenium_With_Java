package Course.POMTests;
import Helpers.BrowserFactory;
import Helpers.ConfigurationReader;
import Helpers.NoSuchBrowserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BaseTests {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static ConfigurationReader configuration;
    @BeforeAll
    public static void loadConfiguration()
    {
    configuration = new ConfigurationReader();
    }
    @BeforeEach
    public void setup() {
        BrowserFactory browser = new BrowserFactory();
        try {
            driver = browser.createDriver(configuration);
        } catch (NoSuchBrowserException e) {
            throw new RuntimeException(e);
        }


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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(selector));
    }
}
