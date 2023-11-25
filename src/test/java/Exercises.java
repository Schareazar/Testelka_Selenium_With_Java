import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class Exercises {
    WebDriver driver;
    @BeforeEach
    public void setup()
    {
        driver = new ChromeDriver();
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
        Assertions.assertEquals(driver.findElement(By.className("tracking-tight")).getText(),
                "Step 2 of 3", "Step 2 not loaded");
    }

}
