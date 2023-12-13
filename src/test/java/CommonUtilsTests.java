import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import java.util.List;

public class CommonUtilsTests extends BaseTests {
    @Test
    public void urlTest()
    {
        String url = "http://localhost:8080/";
        driver.get(url);
        Assertions.assertAll(
                () -> Assertions.assertEquals(url, driver.getCurrentUrl(), "Url different than expected")
        );
    }
    @Test
    public void firstFindBy()
    {
        driver.get("http://localhost:8080/");
        WebElement searchField = driver.findElement(By.id("wc-block-search__input-1"));
        searchField.click();
        List<WebElement> addToCart = driver.findElements(By.className("add_to_cart_button"));
        addToCart.clear();
    }
    @Test
    public void searchBoxShouldHavePlaceholder()
    {
        driver.get("http://localhost:8080/");
        WebElement searchBox = driver.findElement(By.id("wc-block-search__input-1"));
        Assertions.assertEquals("Search products…", searchBox.getDomAttribute("placeholder"),
                "SearchBox placeholder text is incorrect");
    }

}
