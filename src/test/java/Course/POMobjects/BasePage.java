package Course.POMobjects;
import Course.Helpers.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final String baseUrl;
    protected BasePage (WebDriver driver)
   {
       this.driver = driver;
       baseUrl = new ConfigurationReader().getBaseUrl();
       wait = new WebDriverWait(driver, Duration.ofSeconds(5));
   }
    public void waitToDisappear (String cssLocator)
    {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(cssLocator), 0));
    }
    public void waitToAppear(String cssLocator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLocator)));
    }
    public String getText(String cssLocator) {
        return driver.findElement(By.cssSelector(cssLocator)).getText();
    }


}
