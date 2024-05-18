package Exercises;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class Mattermost extends Base{

    @Test
    public void exercise2a()
    {
        bot.mattermostGo("/");
        bot.waitToAppear(".get-app__continue").click();
        bot.waitToAppear(".alternate-link__link").click();
        Assertions.assertEquals((bot.baseUrlMattermost + "/access_problem"), driver.getCurrentUrl(),
                "Access problem page was not displayed");
    }
    @Test
    public void exercise2b()
    {
        adminLogin();
        bot.waitToDisappear("#loadingSpinner");
        Assertions.assertEquals("http://localhost:8065/marcin/channels/town-square", driver.getCurrentUrl(),
                "Town-square homepage was not displayed, login failed");
    }
    @Test
    public void exercise3a()
    {
        adminLogin();
        bot.mattermostGo("/admin_console/user_management/permissions/system_scheme");
        WebElement saveButton = bot.waitToAppear("#saveSetting");
        Assertions.assertFalse(saveButton.isEnabled(), "Save button is not disabled by default");
    }
    @Test
    public void exercise4a()
    {
        adminLogin();
        //it is impossible to create WebElement to refer to because of StaleElementReference
        //after clicking on the element it changes to a new one with the same name
        bot.waitToAppear("#toggleFavorite").click();
        Assertions.assertTrue(bot.find("#toggleFavorite").getDomAttribute("class").contains("active"));
        bot.click("#toggleFavorite");
    }
}
