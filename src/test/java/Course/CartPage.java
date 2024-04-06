package Course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final String baseUrl = "http://localhost:8080";
    private final By cartItems = By.cssSelector("tr.cart_item");
    public WebDriverWait wait;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public void open()
    {
        driver.get(baseUrl + "/cart/");
    }
    public void click(String cssLocator) {
        driver.findElement(By.cssSelector(cssLocator)).click();
    }
    public int getNumberOfProductsInCart()
    {
        return driver.findElements(cartItems).size();
    }
    public void waitToDisappear (String cssLocator)
    {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(cssLocator), 0));
    }
    public void write(String cssLocator, String input) {
       WebElement inputField = driver.findElement(By.cssSelector(cssLocator));
       inputField.clear();
       inputField.sendKeys(input);
    }
}
