package Course.Helpers;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigurationReader {

    private final String propertyNotSpecified = " is not specified in configuration.properties";
    ConfigurationProperties configurationProperties = new ConfigurationProperties();


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
        configurationProperties.Configure(reader);
    }
    public String getBrowser()
    {
        if (!configurationProperties.getBrowser().isEmpty()) return configurationProperties.getBrowser();
        else throw new RuntimeException("Browser" + propertyNotSpecified);
    }
    public boolean isHeadless()
    {
        if (!configurationProperties.getHeadless().isEmpty()) return Boolean.parseBoolean(configurationProperties.getHeadless());
        else throw new RuntimeException("Headless" + propertyNotSpecified);
    }
    public String getBaseUrl()
    {
        if (!configurationProperties.getBaseUrl().isEmpty()) return configurationProperties.getBaseUrl();
        else throw new RuntimeException("BaseUrl" + propertyNotSpecified);
    }
    public int getWaitSeconds()
    {
        if (!configurationProperties.getWaitSeconds().isEmpty()) return Integer.parseInt(configurationProperties.getWaitSeconds());
        else throw new RuntimeException("Wait length in seconds" + propertyNotSpecified);
    }
    public String getTarget()
    {
        if (!configurationProperties.getTarget().isEmpty()) return configurationProperties.getTarget();
        else throw new RuntimeException("Target" + propertyNotSpecified);
    }
    public String getRemoteUrl()
    {
        if (!configurationProperties.getRemoteUrl().isEmpty()) return configurationProperties.getRemoteUrl();
        else throw new RuntimeException("RemoteUrl" + propertyNotSpecified);
    }
    public String getBrowserVersion()
    {
        if (!configurationProperties.getBrowserVersion().isEmpty()) return configurationProperties.getBrowserVersion();
        else throw new RuntimeException("Browser version" + propertyNotSpecified);
    }
}
