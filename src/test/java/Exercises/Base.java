package Exercises;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ActionBot bot;
    @BeforeEach
    public void setup()
    {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);
        bot = new ActionBot(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @AfterEach
    public void teardown()
    {
        driver.quit();
    }
    protected void adminLogin()
    {
        bot.mattermostGo("/login/");
        bot.waitToAppear(".get-app__continue").click();
        bot.waitToAppear("#input_loginId").sendKeys("test");
        bot.write("#input_password-input", "marcin");
        bot.click("#saveSetting");
    }
}
