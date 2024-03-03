package Exercises;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Rally extends Base{

    @Test
    public void exercise3b()
    {
        driver.get(baseUrlRally + "/new");
        WebElement title = driver.findElement(By.id("title"));
        WebElement location = driver.findElement(By.id("location"));
        WebElement description = driver.findElement(By.id("description"));
        Assertions.assertAll
                (
                        () -> Assertions.assertEquals("Monthly Meetup", title.getDomAttribute("placeholder")),
                        () -> Assertions.assertEquals("Joe's Coffee Shop", location.getDomAttribute("placeholder")),
                        () -> Assertions.assertEquals("Hey everyone, please choose the dates that work for you!",
                                description.getDomAttribute("placeholder"))
                );
    }
    @Test
    @Disabled
    public void exercise1() {
        driver.get(baseUrlRally + "/new");
        driver.findElement(By.id("title")).sendKeys("Test event title $%^*");
        driver.findElement(By.id("location")).sendKeys("Test event location żółć");
        driver.findElement(By.id("description")).sendKeys("Test event description /</test>");
        driver.findElement(By.className("btn-primary")).click();
        Assertions.assertEquals("Step 2 of 3", driver.findElement(By.className("tracking-tight")).getText(),
                "Step 2 not loaded");
    }

    @Test
    public void exercise4b()
    {
        exercise1();
        driver.findElement(By.className("z-10")).click();
        driver.findElement(By.className("btn-primary")).click();
        driver.findElement(By.id("name")).sendKeys("Marcin");
        driver.findElement(By.id("contact")).sendKeys("marcin@marcin.com");
        driver.findElement(By.className("btn-primary")).click();
        Assertions.assertEquals("Select your availability and click Continue",
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className("px-1"))).getText(),
                "Poll is not visible");
    }

    @Test
    public void exercise5a()
    {
        driver.get(baseUrlRally + "/admin/" + createPoll());
        driver.get(driver.findElement(By.xpath(".//div[@class='relative']/input")).getDomAttribute("Value"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//textarea[@id='comment']"))).sendKeys("Test comment");
        driver.findElement(By.xpath(".//input[@name='authorName']")).sendKeys("Test author");
        driver.findElement(By.xpath(".//form//button[@type='submit']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("button.pointer-events-none"), 0));
        Assertions.assertTrue(driver.findElement(By.xpath(".//div[@data-testid='comment']")).isDisplayed()
                ,"Comment is not displayed");
    }
}
