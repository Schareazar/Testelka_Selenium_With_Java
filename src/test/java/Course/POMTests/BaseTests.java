package Course.POMTests;
import Helpers.ConfigurationReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
    public void setup()
    {
String browser = configuration.getBrowser();
boolean isHeadless = configuration.isHeadless();

        switch (browser) {
            case "Chrome" -> {
                if (isHeadless) {
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
                } else {
                    driver = new ChromeDriver();
                }
            }
            case "Firefox" -> {
                if (isHeadless) {
                    driver = new FirefoxDriver(new FirefoxOptions().addArguments("-headless"));
                } else {
                    driver = new FirefoxDriver();
                }
            }
            case "Edge" -> {
                if (isHeadless) {
                    driver = new EdgeDriver(new EdgeOptions().addArguments("--headless=new"));
                } else {
                    driver = new EdgeDriver();
                }
            }
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
