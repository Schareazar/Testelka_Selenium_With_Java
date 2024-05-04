package Course.POMTests;
import Course.POMobjects.CartPage;
import org.junit.jupiter.api.*;

public class CartTests extends BaseTests {

    @Test
    public void cart_should_be_empty_by_default()
    {
        CartPage cartpage = new CartPage(browser);
        cartpage.open();
        Assertions.assertEquals(0, cartpage.getNumberOfProductsInCart(),
                "Cart is not empty");
    }
}
