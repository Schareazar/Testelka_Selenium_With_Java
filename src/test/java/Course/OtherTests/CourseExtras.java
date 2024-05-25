package Course.OtherTests;

import Course.POMTests.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.print.PageMargin;
import org.openqa.selenium.print.PageSize;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Base64;

public class CourseExtras extends BaseTests {
    private final String astronomySlug = "history-of-astronomy-by-george-forbes/";
    private final By addToWishlistLocator = By.cssSelector("a.add_to_wishlist");
    private final By removeFromWishlistLocator = By.cssSelector(".delete_item");
    private final By addToCartLocator = By.name("add-to-cart");
    private WebDriver driver;

    @BeforeEach
    public void AdditionalSetup()
    {
        driver = browser.driver;
    }

    @Test
    public void windowSizeAndPositionManipulation()
    {
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
    @Test
    public void fluentWait()
    {
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
    @Test
    public void itemsInCartCookieValueUpdatesCorrectly()
    {
        driver.get("http://localhost:8080/product/" + astronomySlug);
        Find(addToCartLocator).click();
        Find(addToCartLocator).click();

        Cookie itemsInCartCookie = driver.manage().getCookieNamed("woocommerce_items_in_cart");
        int cookieNumber = driver.manage().getCookies().size();
        Cookie newCookie = new Cookie("test", "5");
        driver.manage().addCookie(newCookie);

        Assertions.assertAll(
                () -> Assertions.assertEquals(cookieNumber + 1, driver.manage().getCookies().size()),
                () -> Assertions.assertEquals("2", itemsInCartCookie.getValue())
        );
        driver.manage().deleteAllCookies();
    }
    @Test
    public void canSelectIceCreamFlavor()
    {
        driver.get("https://fakestore.testelka.pl/lista-rozwijana/");
        WebElement flavors = Find(By.cssSelector("select#flavors"));
        Select FlavorsDropDown = new Select(flavors);
        FlavorsDropDown.selectByVisibleText("marakuja");
        FlavorsDropDown.selectByValue("strawberry");
        Assertions.assertEquals("truskawkowy", FlavorsDropDown.getFirstSelectedOption().getText());
    }
    @Test
    public void printPageExample() {
        driver.get("http://localhost:3000/");
        PrintsPage printer = (PrintsPage) driver;
        PrintOptions printOptions = new PrintOptions();
        PageSize pageSize = new PageSize(27.94, 21.59);
        printOptions.setPageSize(pageSize);
        PageMargin pageMargin = new PageMargin(0, 0, 0, 0);
        printOptions.setPageMargin(pageMargin);
        printOptions.setBackground(true);
        printOptions.setScale(0.50);

        Pdf pdf = printer.print(printOptions);
        String pageContent = pdf.getContent();

        Path outputPath = Paths.get("./target/media/pageContent.pdf");

        byte[] decodedPageContent = Base64.getDecoder().decode(pageContent);
        try {
            Files.createDirectories(outputPath.getParent());
            Files.write(outputPath, decodedPageContent);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing the PDF file: " + e);
        }
        Assertions.assertNotEquals(0, decodedPageContent.length);
    }
    @Test
    public void screenshotExample()
    {
        driver.get("http://localhost:3000/");
        WebElement element = Find(By.cssSelector(".items-end.mx-auto"));
        File pageScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File elementScreenshot = element.getScreenshotAs(OutputType.FILE);
        Path pageOutputPath = Paths.get("./target/media/pageScreenshot.jpg");
        Path elementOutputPath = Paths.get("./target/media/element.jpg");
        try {
            Files.copy(pageScreenshot.toPath(), pageOutputPath, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(elementScreenshot.toPath(), elementOutputPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Screenshot save failed" + e);
        }
        Assertions.assertTrue(pageScreenshot.exists() && elementScreenshot.exists());
    }
}
