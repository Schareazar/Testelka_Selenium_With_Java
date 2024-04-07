package Course.POMPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected BasePage (WebDriver driver)
   {
       this.driver = driver;
       wait = new WebDriverWait(driver, Duration.ofSeconds(5));
   }

}
