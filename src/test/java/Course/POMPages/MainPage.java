package Course.POMPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    private final By wishlistFromHeader = By.cssSelector("#menu-item-88 a");
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(baseUrl);
    }

    public WishlistPage goToWishlist()
    {
        driver.findElement(wishlistFromHeader).click();
        return new WishlistPage(driver);
    }
}
