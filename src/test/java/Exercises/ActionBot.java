package Exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionBot {

    private final WebDriver driver;
    private final String baseUrl;
    protected String baseUrlRally = "http://localhost:3000";
    protected String baseUrlMattermost = "http://localhost:8065";

    public ActionBot (WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
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
        driver.findElement(By.cssSelector(cssLocator)).sendKeys(input);
    }
    public WebElement find (String cssLocator)
    {
        return driver.findElement(By.cssSelector(cssLocator));
    }
}
