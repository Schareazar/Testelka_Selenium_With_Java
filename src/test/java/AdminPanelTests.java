import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import java.util.List;

public class AdminPanelTests extends BaseTests{

    private final String newProducts = "post-new.php?post_type=product";
    @BeforeEach
    public void adminLogin()
    {
        driver.get(baseUrl + "/my-account/");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void successfulAdminLoginDisplaysAccountContent()
    {
        Assertions.assertDoesNotThrow(() -> driver.findElement(By.className("woocommerce-MyAccount-content")),
                "Login failed");
    }
    @Test
    public void virtualProductShouldNotBeShippable()
    {
        driver.get(baseUrl + "/wp-admin/" + newProducts);
        driver.findElement(By.id("_virtual")).click();
        WebElement shipping = driver.findElement(By.className("shipping_options"));
        Assertions.assertFalse(shipping.isDisplayed());
    }
    @Test
    public void selectAllShouldTickAllBoxes()
    {
        driver.get(baseUrl + "/wp-admin/" + newProducts);
        driver.findElement(By.id("cb-select-all-1")).click();
        List<WebElement> productCheckboxes = driver.findElements(By.name("post[]"));
        // Testelka solution
//        Assertions.assertEquals(productCheckboxes.size(), productCheckboxes.stream().filter
//         (checkbox -> checkbox.isSelected()).count(), "Not all checkboxes are ticked");
        // My solution
        for (WebElement checkbox: productCheckboxes
        ) {
            Assertions.assertTrue(checkbox.isSelected());
        }
    }
}
