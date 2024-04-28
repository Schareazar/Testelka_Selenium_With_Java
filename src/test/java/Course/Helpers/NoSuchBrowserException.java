package Course.Helpers;

public class NoSuchBrowserException  extends Exception{

    public NoSuchBrowserException(String browser)
    {
        super("Provided browser " + browser + " is not supported");
    }
}
