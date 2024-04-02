package Course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;
    private final String baseUrl = "http://localhost:8080";
    private final By cartItems = By.cssSelector("tr.cart_item");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open()
    {
        driver.get(baseUrl + "/cart/");
    }
    public int getNumberOfProductsInCart()
    {
        return driver.findElements(cartItems).size();
    }
}
