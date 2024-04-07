package Course.OtherTests;

import Course.POMTests.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShadowDOMTests extends BaseTests {

    @Test
    public void shadowDOMChromium()
    {
        String input = "Test";
        driver.get("https://fakestore.testelka.pl/shadow-dom-w-selenium/");
        WebElement shadowHost = driver.findElement(By.cssSelector("#host"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        shadowRoot.findElement(By.cssSelector("#input")).sendKeys(input);
        shadowRoot.findElement(By.cssSelector("button")).click();

        Assertions.assertEquals("Wprowadzony tekst: " + input, shadowRoot.findElement(By.cssSelector("#output")).getText(),
                "Displayed text different than provided input");
    }

    @Test
    public void shadowDOMFirefox()
    {
        String input = "Test";
        driver.get("https://fakestore.testelka.pl/shadow-dom-w-selenium/");
        WebElement shadowHost = driver.findElement(By.cssSelector("#host"));
        //javascript executed in browser
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        List<WebElement> shadowContent = (List<WebElement>) jsExecutor.executeScript
                ("return arguments[0].shadowRoot.children", shadowHost);
        WebElement shadowRoot = shadowContent.getFirst();

        shadowRoot.findElement(By.cssSelector("#input")).sendKeys(input);
        shadowRoot.findElement(By.cssSelector("button")).click();

        Assertions.assertEquals("Wprowadzony tekst: " + input, shadowRoot.findElement(By.cssSelector("#output")).getText(),
                "Displayed text different than provided input");
    }
}
