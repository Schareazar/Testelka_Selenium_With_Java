package Course.POMobjects;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public final StoreHeaderComponent storeHeader;
    public MainPage(WebDriver driver) {
        super(driver);
        storeHeader = new StoreHeaderComponent(driver);
    }

    public void open() {
        driver.get(baseUrl);
    }

}
