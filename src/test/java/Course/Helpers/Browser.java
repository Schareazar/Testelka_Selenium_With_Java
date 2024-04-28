package Course.Helpers;

import org.openqa.selenium.WebDriver;

public record Browser(WebDriver driver, String baseUrl) {
}
