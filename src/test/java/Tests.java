import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.time.Duration;
import java.util.List;

public class Tests {
    WebDriver driver;
    @BeforeEach
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        //Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
        //Duration pageLoadTimeout = driver.manage().timeouts().getPageLoadTimeout();
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
                () -> Assertions.assertEquals("Test App - Just another WordPress site", driver.getTitle(),
                        "Site title different than expected")
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
    public void addingToCartShouldUpdateProductCounter()
    {
        driver.get("http://localhost:8080/");
        WebElement productLink = driver.findElement(By.className("woocommerce-LoopProduct-link"));
        productLink.click();
        WebElement addToCartButton = driver.findElement(By.name("add-to-cart"));
        addToCartButton.click();
        Assertions.assertEquals("1", (driver.findElement(By.className("wc-block-mini-cart__badge")).getText()),
                "Number of products in the cart wasn't updated from 0 to 1");
    }

    @Test
    public void addingToCartShouldUpdateTotalInCart()
    {
        driver.get("http://localhost:8080/product/history-of-astronomy-by-george-forbes/");
        driver.findElement(By.name("add-to-cart")).click();
        driver.findElement(By.className("wc-block-mini-cart__button")).click();
        WebElement totalPrice = driver.findElement(By.className("wc-block-components-totals-item__value"));
        Assertions.assertEquals("20,00 €", totalPrice.getText(),"Total was not updated correctly");

    }

    @Test
    public void successfulAdminLoginDisplaysAccountContent()
    {
        driver.get("http://localhost:8080/my-account/");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        Assertions.assertDoesNotThrow(() -> driver.findElement(By.className("woocommerce-MyAccount-content")),
                "Login failed");


    }

}
