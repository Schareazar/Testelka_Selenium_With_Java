package Course.POMPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage{
    private final String baseUrl = "http://localhost:8080";

    public ProductPage(WebDriver driver)
    {
        super(driver);
    }

    public void open(String productSlug)
    {
        driver.get(baseUrl + "/product/" + productSlug);
    }

    public void click(String cssLocator) {
        driver.findElement(By.cssSelector(cssLocator)).click();
    }
    public void waitToAppear(String cssLocator) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLocator)));
    }
    public void waitToDisappear (String cssLocator)
    {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(cssLocator), 0));
    }
    public String getText(String cssLocator) {
        return driver.findElement(By.cssSelector(cssLocator)).getText();
    }
    public CartPage goToCart()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".woocommerce-message>.button"))).click();
        return new CartPage(driver);
    }
}
