package PageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    private By username = By.id("username");
    private By password = By.id("password");
    private By btnLogin = By.xpath("//input[@value='Login']");
    private By messageWelcome = By.className("account");

    public WebElement getUsername(){ return Constant.DRIVER.findElement(username); }
    public WebElement getPassword(){ return Constant.DRIVER.findElement(password); }
    public WebElement getBtnLogin(){ return Constant.DRIVER.findElement(btnLogin); }

    public void enterUsername(String username){ getUsername().sendKeys(username); }
    public void enterPassword(String password){ getPassword().sendKeys(password); }
    public void clickBtnLogin(){ getBtnLogin().submit(); }

    public void loginToSystem(String username, String password) throws InterruptedException {
        enterUsername(username);
        enterPassword(password);
        clickBtnLogin();
        Thread.sleep(Constant.SHORT_WAIT_MILLIS);
    }
}
