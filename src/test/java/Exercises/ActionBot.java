package Exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
public class ActionBot {

    private final WebDriver driver;
    protected String baseUrlRally = "http://localhost:3000";
    protected String baseUrlMattermost = "http://localhost:8065";
    protected WebDriverWait wait;

    public ActionBot (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait (driver, Duration.ofSeconds(5));
    }
    public void rallyGo (String slug) {
        driver.get(baseUrlRally + slug);
    }
    public void mattermostGo (String slug) {
        driver.get(baseUrlMattermost + slug);
    }
    public int countElements (String cssLocator)
    {
        return driver.findElements(By.cssSelector(cssLocator)).size();
    }
    public void click (String cssLocator)
    {
        driver.findElement(By.cssSelector(cssLocator)).click();
    }
    public void write (String cssLocator, String input)
    {
        driver.findElement(By.cssSelector(cssLocator)).clear();
        driver.findElement(By.cssSelector(cssLocator)).sendKeys(input);
    }
    public WebElement find (String cssLocator)
    {
        return driver.findElement(By.cssSelector(cssLocator));
    }
    public List<WebElement> createList (String cssLocator)
    {
        return driver.findElements(By.cssSelector(cssLocator));
    }
    public void waitToDisappear (String cssLocator)
    {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(cssLocator), 0));
    }
    public WebElement waitToAppear (String cssLocator)
    {
       return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLocator)));
    }
    public String getText (String cssLocator)
    {
        return driver.findElement(By.cssSelector(cssLocator)).getText();
    }
}
