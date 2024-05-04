package Course.POMTests;

import Course.POMobjects.CartPage;
import Course.POMobjects.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductTests extends BaseTests{
    private final String addToCartLocator = "button[name='add-to-cart']";
    private final String updateCartLocator = "button[name='update_cart']";
    private final String astronomySlug = "history-of-astronomy-by-george-forbes/";

    @Test
    public void updatingCartShouldBeDisabledIfQuantityIsUnchanged()
    {
        ProductPage productPage = new ProductPage(browser);
        productPage.open(astronomySlug);
        productPage.click(addToCartLocator);
        productPage.goToCart();
        Assertions.assertFalse(Find(By.cssSelector(updateCartLocator)).isEnabled(),
                "Cart update button was enabled even though quantity wasn't changed");
    }
    @Test
    public void updatingQuantityShouldChangeTotalPrice()
    {
        ProductPage productPage = new ProductPage(browser);
        productPage.open(astronomySlug);
        productPage.click(addToCartLocator);
        CartPage cartPage = productPage.goToCart();
        String totalBefore = Find(By.className("order-total")).getText();
        WebElement quantityField = Find(By.className("qty"));
        cartPage.write(".qty","2");
        quantityField.clear();
        quantityField.sendKeys("2");
        cartPage.click(updateCartLocator);
        cartPage.waitToDisappear(".blockUI");
        WebElement totalAfter = Find(By.className("order-total"));
        Assertions.assertNotEquals(totalBefore ,totalAfter.getText(),
                "Total wasn't changed after cart update");
    }

    @Test
    public void addingToCartShouldUpdateProductCounter()
    {
        ProductPage productPage = new ProductPage(browser);
        productPage.open(astronomySlug);
        productPage.click(addToCartLocator);
        productPage.waitToAppear(".wc-block-mini-cart__badge");
        Assertions.assertEquals("1", (productPage.getText(".wc-block-mini-cart__badge")),
                "Number of products in the cart wasn't updated from 0 to 1");
    }
    @Test
    public void addingToCartShouldUpdateTotalInCart()
    {
        ProductPage productPage = new ProductPage(browser);
        productPage.open(astronomySlug);
        productPage.click(addToCartLocator);
        productPage.click(".wc-block-mini-cart__button");
        productPage.waitToAppear(".wc-block-components-totals-item__value");
        Assertions.assertEquals("20,00 €", Find(By.cssSelector(".wc-block-components-totals-item__value")).getText(),
                "Total was not updated correctly");

    }
    @Test
    public void defaultProductQuantityShouldBe1()
    {
        ProductPage productPage = new ProductPage(browser);
        productPage.open(astronomySlug);
        Assertions.assertEquals("1",  Find(By.className("qty")).getDomProperty("defaultValue"),
                "Default value is different than 1");
    }
}
