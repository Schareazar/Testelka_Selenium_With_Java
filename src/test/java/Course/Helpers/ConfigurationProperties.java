package Course.Helpers;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationProperties {

    private String browser;
    private String baseUrl;
    private String headless;
    private String waitSeconds;
    private String target;
    private String remoteUrl;
    private String browserVersion;

    Properties properties = new Properties();

    protected void Configure(BufferedReader reader) {
        try {
            properties.load(reader);
            reader.close();
        } catch (
                IOException e) {
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
    public String getBrowser() {
        return browser;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getHeadless() {
        return headless;
    }

    public String getWaitSeconds() {
        return waitSeconds;
    }

    public String getTarget() {
        return target;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }
    }
