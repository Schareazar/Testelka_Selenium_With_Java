import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Course {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeEach
    public void setup()
    {
        driver = new ChromeDriver();
        // implicitWait shouldn't be used when webDriverWait is used
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        WebElement totalPrice = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("wc-block-mini-cart__badge")));
        Assertions.assertEquals("1", (totalPrice.getText()),
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

    @Test
    public void updatingQuantityShouldChangeTotalPrice()
    {
        driver.get("http://localhost:8080/product/history-of-astronomy-by-george-forbes/");
        driver.findElement(By.name("add-to-cart")).click();
        driver.get("http://localhost:8080/cart/");
        String totalBefore = driver.findElement(By.className("order-total")).getText();
        WebElement quantityField = driver.findElement(By.className("qty"));
        quantityField.clear();
        quantityField.sendKeys("2");
        driver.findElement(By.name("update_cart")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));
        WebElement totalAfter = driver.findElement(By.className("order-total"));
        Assertions.assertNotEquals(totalBefore ,totalAfter.getText(),
                "Total wasn't changed after cart update");
    }

}
