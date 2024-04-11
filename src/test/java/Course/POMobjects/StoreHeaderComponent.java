package Course.POMobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreHeaderComponent extends BasePage{
    private final By wishlistFromHeader = By.cssSelector("#menu-item-88 a");
    protected StoreHeaderComponent(WebDriver driver) {
        super(driver);
    }
    public WishlistPage goToWishlist()
    {
        driver.findElement(wishlistFromHeader).click();
        return new WishlistPage(driver);
    }
}
