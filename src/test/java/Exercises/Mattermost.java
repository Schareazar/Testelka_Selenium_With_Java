package Exercises;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Mattermost extends Base{

    private void adminLogin()
    {
        bot.mattermostGo("/login/");
        wait.until(driver -> driver.findElement(By.className("get-app__continue"))).click();
        wait.until(driver -> driver.findElement(By.name("loginId"))).sendKeys("test");
        bot.write("#input_password-input", "marcin");
        bot.click("#saveSetting");
    }
    @Test
    public void exercise2a()
    {
        bot.mattermostGo("/");
        wait.until(driver -> driver.findElement(By.className("get-app__continue"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alternate-link__link"))).click();
        Assertions.assertEquals((bot.baseUrlMattermost + "/access_problem"), driver.getCurrentUrl(),
                "Access problem page was not displayed");
    }
    @Test
    public void exercise2b()
    {
        adminLogin();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.id("loadingSpinner"), 0));
        Assertions.assertEquals("http://localhost:8065/marcin/channels/town-square", driver.getCurrentUrl(),
                "Town-square homepage was not displayed, login failed");
    }
    @Test
    public void exercise3a()
    {
        adminLogin();
        bot.mattermostGo("/admin_console/user_management/permissions/system_scheme");
        WebElement saveButton = wait.until(ExpectedConditions.presenceOfElementLocated((By.id("saveSetting"))));
        Assertions.assertFalse(saveButton.isEnabled(), "Save button is not disabled by default");
    }
    @Test
    public void exercise4a()
    {
        adminLogin();
        //it is impossible to create WebElement to refer to because of StaleElementReference
        //after clicking on the element it changes to a new one with the same name
        bot.click("#toggleFavorite");
        Assertions.assertTrue(bot.find("#toggleFavorite").getDomAttribute("class").contains("active"));
        bot.click("#toggleFavorite");
    }
}
