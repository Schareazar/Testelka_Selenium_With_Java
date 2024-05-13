package Course.Helpers;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private final String browser;
    private final String baseUrl;
    private final String headless;
    private final String waitSeconds;
    private final String target;
    private final String remoteUrl;
    private final String browserVersion;
    private final String propertyNotSpecified = " is not specified in configuration.properties";

   public ConfigurationReader()
    {
        String configurationPath = "src/test/resources/configuration.properties";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(configurationPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration file not found at: " + configurationPath);
        }
        Properties properties = new Properties();
        try {
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        baseUrl = properties.getProperty("baseUrl");
        browser = properties.getProperty("browser");
        headless = properties.getProperty("headless");
        waitSeconds = properties.getProperty("waitSeconds");
        target = properties.getProperty("target");
        remoteUrl = properties.getProperty("remoteUrl");
        browserVersion = properties.getProperty("browserVersion");
    }
    public String getBrowser()
    {
        if (!browser.isEmpty()) return browser;
        else throw new RuntimeException("Browser" + propertyNotSpecified);
    }
    public boolean isHeadless()
    {
        if (!headless.isEmpty()) return Boolean.parseBoolean(headless);
        else throw new RuntimeException("Headless" + propertyNotSpecified);
    }
    public String getBaseUrl()
    {
        if (!baseUrl.isEmpty()) return baseUrl;
        else throw new RuntimeException("BaseUrl" + propertyNotSpecified);
    }
    public int getWaitSeconds()
    {
        if (!waitSeconds.isEmpty()) return Integer.parseInt(waitSeconds);
        else throw new RuntimeException("Wait length in seconds" + propertyNotSpecified);
    }
    public String getTarget()
    {
        if (!target.isEmpty()) return target;
        else throw new RuntimeException("Target" + propertyNotSpecified);
    }
    public String getRemoteUrl()
    {
        if (!remoteUrl.isEmpty()) return remoteUrl;
        else throw new RuntimeException("RemoteUrl" + propertyNotSpecified);
    }
    public String getBrowserVersion()
    {
        if (!browserVersion.isEmpty()) return browserVersion;
        else throw new RuntimeException("Browser version" + propertyNotSpecified);
    }
}
