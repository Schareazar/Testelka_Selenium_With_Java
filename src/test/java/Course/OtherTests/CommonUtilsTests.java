package Course.OtherTests;
import Course.POMTests.BaseTests;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import java.util.List;

public class CommonUtilsTests extends BaseTests {
    @Test
    public void urlTest()
    {
        WebDriver driver = browser.driver;
        driver.get("http://localhost:8080/");
        Assertions.assertAll(
                () -> Assertions.assertEquals("http://localhost:8080/", driver.getCurrentUrl(), "Url different than expected")
        );
    }
    @Test
    public void firstFindBy()
    {
        WebDriver driver = browser.driver;
        driver.get("http://localhost:8080/");
        WebElement searchField = Find(By.id("wc-block-search__input-1"));
        searchField.click();
        List<WebElement> addToCart = FindMany(By.className("add_to_cart_button"));
        addToCart.clear();
    }
    @Test
    public void searchBoxShouldHavePlaceholder()
    {
        WebDriver driver = browser.driver;
        driver.get("http://localhost:8080/");
        WebElement searchBox = Find(By.id("wc-block-search__input-1"));
        Assertions.assertEquals("Search products…", searchBox.getDomAttribute("placeholder"),
                "SearchBox placeholder text is incorrect");
    }
    @Test
    public void windowSizeAndPositionManipulation()
    {
        WebDriver driver = browser.driver;
        driver.manage().window().setSize(new Dimension(1600, 900));
        driver.manage().window().setPosition(new Point(0,1));
        //driver.manage().window().fullscreen();
        driver.navigate().refresh();
        driver.navigate().to("http://localhost:8080/");
        Dimension size = driver.manage().window().getSize();
        Point position = driver.manage().window().getPosition();
        String pageSource = driver.getPageSource();
        Assertions.assertAll(
                () -> Assertions.assertEquals(1600, size.getWidth()),
                () -> Assertions.assertEquals(900, size.getHeight()),
                () -> Assertions.assertEquals(0, position.getX()),
                () -> Assertions.assertEquals(1, position.getY()),
                () -> Assertions.assertFalse(pageSource.isEmpty())
        );
    }
}
