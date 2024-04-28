package Course.POMobjects;
import org.openqa.selenium.By;
import Course.Helpers.Browser;

public class StoreHeaderComponent extends BasePage{
    private final By wishlistFromHeader = By.cssSelector("#menu-item-88 a");
    protected StoreHeaderComponent(Browser browser) {
        super(browser);
    }
    public WishlistPage goToWishlist()
    {
        driver.findElement(wishlistFromHeader).click();
        return new WishlistPage(browser);
    }
}
