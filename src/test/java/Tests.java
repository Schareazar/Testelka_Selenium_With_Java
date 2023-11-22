import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.util.List;

public class Tests {
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
    public void urlTest()
    {
        String url = "http://localhost:8080/";
        driver.get(url);
        Assertions.assertAll(
                () -> Assertions.assertEquals(url, driver.getCurrentUrl(), "Url different than expected"),
                () -> Assertions.assertEquals("Test App - Just another WordPress site", driver.getTitle(), "Site title different than expected")
        );
    }
    @Test
    public void firstFindBy()
    {
        driver.get("http://localhost:8080/");
        WebElement searchField = driver.findElement(By.id("wc-block-search__input-1"));
        searchField.click();
        List<WebElement> addToCart = driver.findElements(By.className("add_to_cart_button"));
    }


}
