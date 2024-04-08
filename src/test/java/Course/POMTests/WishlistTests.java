package Course.POMTests;
import Course.POMPages.MainPage;
import Course.POMPages.ProductPage;
import Course.POMPages.WishlistPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class WishlistTests extends BaseTests{
    private final String astronomySlug = "history-of-astronomy-by-george-forbes/";

    @Test
    public void wishlist_should_be_empty_by_default() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        WishlistPage wishlistPage = mainPage.goToWishlist();
        Assertions.assertEquals(0, wishlistPage.getNumberOfProducts(),
                "There are some products on wishlist");
    }
    @Test
    public void wishlist_counter_should_update() {
        ProductPage productPage = new ProductPage(driver);
        productPage.open(astronomySlug);
        WishlistPage wishlistPage = productPage.addToWishlist().goToWishlist();
        Assertions.assertNotEquals(0, wishlistPage.getNumberOfProducts(),
                "There are no products on wishlist");
    }
}
