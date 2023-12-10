import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Exercises {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeEach
    public void setup()
    {
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }
    @AfterEach
    public void teardown()
    {
        driver.quit();
    }

    @Test
    public void exercise1() {
        driver.get("http://localhost:3000/new");
        driver.findElement(By.id("title")).sendKeys("Test event title $%^*");
        driver.findElement(By.id("location")).sendKeys("Test event location żółć");
        driver.findElement(By.id("description")).sendKeys("Test event description /</test>");
        driver.findElement(By.className("btn-primary")).click();
        Assertions.assertEquals("Step 2 of 3", driver.findElement(By.className("tracking-tight")).getText(),
                "Step 2 not loaded");
    }

    @Test
    public void exercise2a()
    {
        driver.get("http://localhost:8065/");
        wait.until(driver -> driver.findElement(By.className("get-app__continue"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alternate-link__link"))).click();
        Assertions.assertEquals("http://localhost:8065/access_problem", driver.getCurrentUrl(),
                "Access problem page was not displayed");
    }
    @Test
    public void exercise2b()
    {
        driver.get("http://localhost:8065/login/");
        wait.until(driver -> driver.findElement(By.className("get-app__continue"))).click();
        wait.until(driver -> driver.findElement(By.name("loginId"))).sendKeys("test");
        driver.findElement(By.name("password-input")).sendKeys("marcin");
        driver.findElement(By.id("saveSetting")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.id("loadingSpinner"), 0));
        Assertions.assertEquals("http://localhost:8065/marcin/channels/town-square", driver.getCurrentUrl(),
                "Town-square homepage was not displayed, login failed");
    }

    @Test
    public void exercise3a()
    {
        driver.get("http://localhost:8065/login/");
        wait.until(driver -> driver.findElement(By.className("get-app__continue"))).click();
        wait.until(driver -> driver.findElement(By.name("loginId"))).sendKeys("test");
        driver.findElement(By.name("password-input")).sendKeys("marcin");
        driver.findElement(By.id("saveSetting")).click();
        driver.get("http://localhost:8065/admin_console/user_management/permissions/system_scheme");
        WebElement saveButton = wait.until(ExpectedConditions.presenceOfElementLocated((By.id("saveSetting"))));
        Assertions.assertFalse(saveButton.isEnabled(), "Save button is not disabled by default");
    }

    @Test
    public void exercise3b()
    {
        driver.get("http://localhost:3000/new");
        WebElement title = driver.findElement(By.id("title"));
        WebElement location = driver.findElement(By.id("location"));
        WebElement description = driver.findElement(By.id("description"));
        Assertions.assertAll
                (
                        () -> Assertions.assertEquals("Monthly Meetup", title.getDomAttribute("placeholder")),
                        () -> Assertions.assertEquals("Joe's Coffee Shop", location.getDomAttribute("placeholder")),
                        () -> Assertions.assertEquals("Hey everyone, please choose the dates that work for you!",
                                description.getDomAttribute("placeholder"))
                );

    }


}
