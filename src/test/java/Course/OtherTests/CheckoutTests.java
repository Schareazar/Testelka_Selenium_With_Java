package Course.OtherTests;

import Course.POMTests.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;

public class CheckoutTests extends BaseTests {

    private final By addToCartLocator = By.name("add-to-cart");
    private final String astronomySlug = "history-of-astronomy-by-george-forbes/";
    private final By addToWishlistLocator = By.cssSelector("a.add_to_wishlist");
    private final By removeFromWishlistLocator = By.cssSelector(".delete_item");

    @Test
    public void CanProvideCardDataInIframe()
    {
            WebDriver driver = browser.driver;
            driver.get("http://localhost:8080/product/" + astronomySlug);
            Find(addToCartLocator).click();
            driver.get("http://localhost:8080/checkout/");

            Find(By.cssSelector("#billing_first_name")).sendKeys("Jan");
            Find(By.cssSelector("#billing_last_name")).sendKeys("Testowy");
            Find(By.cssSelector("#billing_address_1")).sendKeys("ul. Ciekawa");
            Find(By.cssSelector("#billing_postcode")).sendKeys("05-250");
            Find(By.cssSelector("#billing_city")).sendKeys("Radzymin");
            Find(By.cssSelector("#billing_phone")).sendKeys("500555555");
            Find(By.cssSelector("#billing_email")).sendKeys("test@gmail.com");

            SwitchFrame(By.cssSelector("#stripe-card-element iframe"));
            Find(By.cssSelector("[name=cardnumber]")).sendKeys("4242424242424242");
            driver.switchTo().defaultContent();
            SwitchFrame(By.cssSelector("#stripe-exp-element iframe"));
            Find(By.cssSelector("[name=exp-date]")).sendKeys("444");
            driver.switchTo().defaultContent();
            SwitchFrame(By.cssSelector("#stripe-cvc-element iframe"));
            Find(By.cssSelector("[name=cvc]")).sendKeys("555");
            driver.switchTo().defaultContent();

            wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"),0));
            Find(By.cssSelector("#place_order")).click();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"),0));

            Assertions.assertEquals("Order received", Find(By.cssSelector(".entry-title")).getText(),
                    "Order not successfully received");
    }
    @Test
    public void fluentWait()
    {
        WebDriver driver = browser.driver;
        driver.get("http://localhost:8080/product/" + astronomySlug);
        Find(addToWishlistLocator).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(7))
                .pollingEvery(Duration.ofNanos(200))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.presenceOfElementLocated(removeFromWishlistLocator));
        driver.findElement(removeFromWishlistLocator).click();
        Assertions.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(addToWishlistLocator)).isDisplayed());
    }
}
