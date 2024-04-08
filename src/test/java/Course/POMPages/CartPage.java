package Course.POMPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage{

    private final By cartItems = By.cssSelector("tr.cart_item");
    public CartPage(WebDriver driver) {
        super(driver);
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
