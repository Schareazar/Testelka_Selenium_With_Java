package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    public WebDriver createDriver(ConfigurationReader configuration) throws NoSuchBrowserException {
        String browser = configuration.getBrowser();

        switch (browser) {
            case "Chrome" -> {
                return createChromeInstance(configuration);
            }
            case "Firefox" -> {
               return createFirefoxInstance(configuration);
            }
            case "Edge" -> {
               return createEdgeInstance(configuration);
            }
            default -> throw new NoSuchBrowserException(browser);
        }
    }

    private WebDriver createChromeInstance (ConfigurationReader configuration)
    {
        if (configuration.isHeadless()) {
            return new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
        } else {
            return new ChromeDriver();
        }
    }
    private WebDriver createFirefoxInstance (ConfigurationReader configuration)
    {
        if (configuration.isHeadless()) {
            return new FirefoxDriver(new FirefoxOptions().addArguments("-headless"));
        } else {
            return new FirefoxDriver();
        }
    }
    private WebDriver createEdgeInstance (ConfigurationReader configuration)
    {
        if (configuration.isHeadless()) {
            return new EdgeDriver(new EdgeOptions().addArguments("--headless=new"));
        } else {
            return new EdgeDriver();
        }
    }
}
