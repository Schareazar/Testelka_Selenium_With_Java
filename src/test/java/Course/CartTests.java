package Course;

import org.junit.jupiter.api.*;

public class CartTests extends BaseTests {

    CartPage cartpage = new CartPage(driver);

    @Test
    public void cart_should_be_empty_by_default()
    {
        cartpage.open();
        Assertions.assertEquals(0, cartpage.getNumberOfProductsInCart(),
                "Cart is not empty");
    }
}
