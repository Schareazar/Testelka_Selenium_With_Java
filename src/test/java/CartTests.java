import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

public class CartTests extends BaseTests {
    @Test
    public void updatingCartShouldBeDisabledIfQuantityIsUnchanged()
    {
        driver.get("http://localhost:8080/product/history-of-astronomy-by-george-forbes/");
        driver.findElement(By.name("add-to-cart")).click();
        driver.get("http://localhost:8080/cart/");

        WebElement updateButton = driver.findElement(By.name("update_cart"));
        Assertions.assertFalse(updateButton.isEnabled(),
                "Cart update button was enabled even though quantity wasn't changed");
    }
    @Test
    public void updatingQuantityShouldChangeTotalPrice()
    {
        driver.get("http://localhost:8080/product/history-of-astronomy-by-george-forbes/");
        driver.findElement(By.name("add-to-cart")).click();
        driver.get("http://localhost:8080/cart/");
        String totalBefore = driver.findElement(By.className("order-total")).getText();
        WebElement quantityField = driver.findElement(By.className("qty"));
        quantityField.clear();
        quantityField.sendKeys("2");
        driver.findElement(By.name("update_cart")).click();
        //wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));
        wait.until(driver -> driver.findElements(By.className("blockUI")).size() == 0);
        WebElement totalAfter = driver.findElement(By.className("order-total"));
        Assertions.assertNotEquals(totalBefore ,totalAfter.getText(),
                "Total wasn't changed after cart update");
    }
    @Test
    public void alternativeUpdatingQuantityShouldChangeTotalPrice()
    {
        driver.get("http://localhost:8080/product/history-of-astronomy-by-george-forbes/");
        driver.findElement(By.name("add-to-cart")).click();
        driver.get("http://localhost:8080/cart/");
        String totalBefore = driver.findElement(By.className("order-total")).findElement(By.className("amount")).getText();
        WebElement quantityField = driver.findElement(By.className("qty"));
        quantityField.clear();
        quantityField.sendKeys("2");
        driver.findElement(By.name("update_cart")).click();
        //wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));
        wait.until(driver -> driver.findElements(By.className("blockUI")).size() == 0);
        WebElement totalAfter = driver.findElement(By.className("order-total")).findElement(By.className("amount"));
        Assertions.assertNotEquals(totalBefore ,totalAfter.getText(),
                "Total wasn't changed after cart update");
    }
    @Test
    public void addingToCartShouldUpdateProductCounter()
    {
        driver.get("http://localhost:8080/");
        WebElement productLink = driver.findElement(By.className("woocommerce-LoopProduct-link"));
        productLink.click();
        WebElement addToCartButton = driver.findElement(By.name("add-to-cart"));
        addToCartButton.click();
        //  (By.className("wc-block-mini-cart__badge")));
        WebElement totalPrice = wait.until(driver -> driver.findElement(By.className("wc-block-mini-cart__badge")));

        Assertions.assertEquals("1", (totalPrice.getText()),
                "Number of products in the cart wasn't updated from 0 to 1");
    }
    @Test
    public void addingToCartShouldUpdateTotalInCart()
    {
        driver.get("http://localhost:8080/product/history-of-astronomy-by-george-forbes/");
        driver.findElement(By.name("add-to-cart")).click();
        driver.findElement(By.className("wc-block-mini-cart__button")).click();
        WebElement totalPrice = driver.findElement(By.className("wc-block-components-totals-item__value"));
        Assertions.assertEquals("20,00 €", totalPrice.getText(),"Total was not updated correctly");

    }
    @Test
    public void defaultProductQuantityShouldBe1()
    {
        driver.get("http://localhost:8080/product/history-of-astronomy-by-george-forbes/");
        WebElement defaultQuantity = driver.findElement(By.className("qty"));
        Assertions.assertEquals("1",  defaultQuantity.getDomProperty("defaultValue"),
                "Default value is different than 1");
    }
}
