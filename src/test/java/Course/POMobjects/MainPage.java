package Course.POMobjects;
import Course.Helpers.Browser;

public class MainPage extends BasePage {

    public final StoreHeaderComponent storeHeader;

    public MainPage(Browser browser) {
        super(browser);
        storeHeader = new StoreHeaderComponent(browser);
    }
    public void open() {
        driver.get(baseUrl);
    }

}
