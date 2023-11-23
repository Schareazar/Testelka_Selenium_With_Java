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
        addToCart.clear();
    }

    @Test
    public void addingToCart()
    {
        driver.get("http://localhost:8080/");
        WebElement productLink = driver.findElement(By.className("woocommerce-LoopProduct-link"));
        productLink.click();
        WebElement addToCartButton = driver.findElement(By.name("add-to-cart"));
        addToCartButton.click();
        Assertions.assertEquals("1", (driver.findElement(By.className("wc-block-mini-cart__badge")).getText()), "Product not added to the cart");
    }
    @Test
    public void login()
    {

    }

}
