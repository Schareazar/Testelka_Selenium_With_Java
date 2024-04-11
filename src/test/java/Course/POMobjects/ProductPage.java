package Course.POMobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage{

    public final StoreHeaderComponent storeHeader;
    private final By addToWishlist = By.cssSelector("a.add_to_wishlist");
    private final String blockingLoader = ".blockUI";
    public ProductPage(WebDriver driver)
    {
        super(driver);
        storeHeader = new StoreHeaderComponent(driver);
    }
    public void open(String productSlug)
    {
        driver.get(baseUrl + "/product/" + productSlug);
    }
    public void click(String cssLocator) {
        driver.findElement(By.cssSelector(cssLocator)).click();
    }

    public CartPage goToCart()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".woocommerce-message>.button"))).click();
        return new CartPage(driver);
    }

    public ProductPage addToWishlist() {
        driver.findElement(addToWishlist).click();
        waitToDisappear(blockingLoader);
        return this;
    }
}
