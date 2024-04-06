package Course;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;
    private final String baseUrl = "http://localhost:8080";
    private final WebDriverWait wait;

    public ProductPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait (driver, Duration.ofSeconds(5));
    }

    public void open(String productSlug)
    {
        driver.get(baseUrl + "/product/" + productSlug);
    }

    public void click(String cssLocator) {
        driver.findElement(By.cssSelector(cssLocator)).click();
    }
    public void waitToAppear(String cssLocator) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLocator)));
    }
    public void waitToDisappear (String cssLocator)
    {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(cssLocator), 0));
    }
    public String getText(String cssLocator) {
        return driver.findElement(By.cssSelector(cssLocator)).getText();
    }
    public CartPage goToCart()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".woocommerce-message>.button"))).click();
        return new CartPage(driver);
    }
}
