package Course.POMobjects;
import Course.Helpers.Browser;
import org.openqa.selenium.By;

public class WishlistPage extends BasePage{

    private final By productItems = By.cssSelector(".wishlist-items-wrapper tr td.product-remove");
    protected WishlistPage(Browser browser) {
        super(browser);
    }
    public int getNumberOfProducts()
    {
        return driver.findElements(productItems).size();
    }
}
