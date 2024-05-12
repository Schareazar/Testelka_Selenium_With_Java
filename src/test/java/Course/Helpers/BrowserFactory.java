package Course.Helpers;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {

    private final String targetErrorMessage = "Target provided in configuration.properties is not correct";
    public Browser createInstance(ConfigurationReader configuration) throws NoSuchBrowserException
    {
       WebDriver driver = createDriver(configuration);
       return new Browser(driver,configuration);
    }
    private WebDriver createDriver(ConfigurationReader configuration) throws NoSuchBrowserException {
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
        ChromeOptions options = new ChromeOptions();
        if (configuration.isHeadless())
        {
            options.addArguments("--headless=new");
        }
        switch (configuration.getTarget())
        {
            case "local" ->
            {
                return new ChromeDriver(options);
            }
            case "remote" ->
            {
                return createRemoteDriver(configuration, options);
            }
            default -> throw new RuntimeException(targetErrorMessage);
        }

    }
    private WebDriver createFirefoxInstance (ConfigurationReader configuration)
    {
        FirefoxOptions options = new FirefoxOptions();
        if (configuration.isHeadless())
        {
            options.addArguments("-headless");
        }
        switch (configuration.getTarget())
        {
            case "local" ->
            {
                return new FirefoxDriver(options);
            }
            case "remote" ->
            {
                return createRemoteDriver(configuration, options);
            }
            default -> throw new RuntimeException(targetErrorMessage);
        }
    }
    private WebDriver createEdgeInstance (ConfigurationReader configuration)
    {
        EdgeOptions options = new EdgeOptions();
        if (configuration.isHeadless())
        {
            options.addArguments("--headless=new");
        }
        switch (configuration.getTarget())
        {
            case "local" ->
            {
                return new EdgeDriver(options);
            }
            case "remote" ->
            {
                return createRemoteDriver(configuration, options);
            }
            default -> throw new RuntimeException(targetErrorMessage);
        }
    }
    private RemoteWebDriver createRemoteDriver(ConfigurationReader configuration, MutableCapabilities options)
    {
        try {
            return new RemoteWebDriver(new URL(configuration.getRemoteUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("RemoteUrl provided in configuration.properties is not correct");
        }
    }
}
