package Course.POMTests;
import Course.Helpers.BrowserFactory;
import Course.Helpers.ConfigurationReader;
import Course.Helpers.NoSuchBrowserException;
import Course.Helpers.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BaseTests {
    protected Browser browser;
    protected WebDriverWait wait;
    private static ConfigurationReader configuration;

    @BeforeAll
    public static void loadConfiguration()
    {
    configuration = new ConfigurationReader();
    }
    @BeforeEach
    public void setup() {
        BrowserFactory browserFactory = new BrowserFactory();
        try {
           browser = browserFactory.createInstance(configuration);
        } catch (NoSuchBrowserException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterEach
    public void teardown()
    {
        browser.driver.quit();
    }

    public WebElement Find(By selector)
    {
        return browser.driver.findElement(selector);
    }
    public List<WebElement> FindMany(By selector)
    {
        return browser.driver.findElements(selector);
    }
    public void SwitchFrame(By selector)
    {
        wait = new WebDriverWait(browser.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(selector));
    }
}
