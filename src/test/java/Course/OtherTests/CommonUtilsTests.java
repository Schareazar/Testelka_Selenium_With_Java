package Course.OtherTests;

import Course.POMTests.BaseTests;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import java.util.List;

public class CommonUtilsTests extends BaseTests {
    @Test
    public void urlTest()
    {
        driver.get(baseUrl + "/");
        Assertions.assertAll(
                () -> Assertions.assertEquals("http://localhost:8080/", driver.getCurrentUrl(), "Url different than expected")
        );
    }
    @Test
    public void firstFindBy()
    {
        driver.get(baseUrl + "/");
        WebElement searchField = driver.findElement(By.id("wc-block-search__input-1"));
        searchField.click();
        List<WebElement> addToCart = driver.findElements(By.className("add_to_cart_button"));
        addToCart.clear();
    }
    @Test
    public void searchBoxShouldHavePlaceholder()
    {
        driver.get(baseUrl + "/");
        WebElement searchBox = driver.findElement(By.id("wc-block-search__input-1"));
        Assertions.assertEquals("Search products…", searchBox.getDomAttribute("placeholder"),
                "SearchBox placeholder text is incorrect");
    }

}
