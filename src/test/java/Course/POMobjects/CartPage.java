package Course.POMobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Course.Helpers.Browser;

public class CartPage extends BasePage{

    private final By cartItems = By.cssSelector("tr.cart_item");
    public final StoreHeaderComponent storeHeader;

    public CartPage(Browser browser) {
        super(browser);
        storeHeader = new StoreHeaderComponent(browser);
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
    public void write(String cssLocator, String input) {
       WebElement inputField = driver.findElement(By.cssSelector(cssLocator));
       inputField.clear();
       inputField.sendKeys(input);
    }
}
