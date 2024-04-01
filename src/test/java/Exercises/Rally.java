package Exercises;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Rally extends Base{

    @Test
    @Disabled
    public void exercise1() {
        bot.rallyGo("/new");
        driver.findElement(By.id("title")).sendKeys("Test event title $%^*");
        driver.findElement(By.id("location")).sendKeys("Test event location żółć");
        driver.findElement(By.id("description")).sendKeys("Test event description /</test>");
        driver.findElement(By.className("btn-primary")).click();
        Assertions.assertEquals("Step 2 of 3", driver.findElement(By.className("tracking-tight")).getText(),
                "Step 2 not loaded");
    }
    @Test
    public void exercise3b()
    {
        bot.rallyGo("/new");
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
        bot.rallyGo("/admin/" + createPoll());
        driver.get(driver.findElement(By.xpath(".//div[@class='relative']/input")).getDomAttribute("Value"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//textarea[@id='comment']"))).sendKeys("Test comment");
        driver.findElement(By.xpath(".//input[@name='authorName']")).sendKeys("Test author");
        driver.findElement(By.xpath(".//form//button[@type='submit']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("button.pointer-events-none"), 0));
        Assertions.assertTrue(driver.findElement(By.xpath(".//div[@data-testid='comment']")).isDisplayed()
                ,"Comment is not displayed");
    }

    @Test
    public void exercise5b()
            //celowo beznadziejne lokatory na stronie
    {
        String startingHour = "1:00 PM";
        String endingHour = "1:30 PM";

        exercise1();
        driver.findElement(By.xpath(".//span[contains(text(),'10')]")).click();
        driver.findElement(By.xpath(".//span[contains(text(),'11')]")).click();
        driver.findElement(By.xpath(".//span[contains(text(),'15')]")).click();
        driver.findElement(By.xpath(".//button[@data-testid='specify-times-switch']")).click();
        driver.findElement(By.xpath(".//span[contains(text(), '12:00 PM')]")).click();
        driver.findElement(By.xpath(".//span[contains(text(), '" + startingHour + "')]")).click();
        driver.findElements(By.cssSelector("[id*=headlessui-menu-button] button:not([role])")).get(0).click();
        driver.findElement(By.xpath(".//button[@role='menuitem'][1]")).click();

        List <WebElement> meetingHours =
                driver.findElements(By.xpath(".//div[@class='divide-y']//button[contains (@id, 'headlessui-listbox')]"));
        Assertions.assertAll(
                () ->    Assertions.assertEquals(startingHour, meetingHours.get(0).getText(), "Starting hour incorrect"),
                () ->    Assertions.assertEquals(startingHour, meetingHours.get(2).getText(), "Starting hour incorrect"),
                () ->    Assertions.assertEquals(startingHour, meetingHours.get(4).getText(), "Starting hour incorrect"),
                () ->    Assertions.assertEquals(endingHour, meetingHours.get(1).getText(), "Ending hour incorrect"),
                () ->    Assertions.assertEquals(endingHour, meetingHours.get(3).getText(), "Ending hour incorrect"),
                () ->    Assertions.assertEquals(endingHour, meetingHours.get(5).getText(), "Ending hour incorrect")
        );

    }
}
