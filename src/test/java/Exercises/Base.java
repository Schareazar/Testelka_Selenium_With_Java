package Exercises;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrlMattermost = "http://localhost:8065";
    protected String baseUrlRally = "http://localhost:3000";

    @BeforeEach
    public void setup()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @AfterEach
    public void teardown()
    {
        driver.quit();
    }
}
