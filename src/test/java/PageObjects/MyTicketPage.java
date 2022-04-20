package PageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyTicketPage extends BasePage{
    private By btnCancel(String departStation, String arriveStation){
        return By.xpath("//table[@class='MyTable']//td[text()='" + departStation
                + "']/following-sibling::td[text()='" + arriveStation + "']/../td/input[@value='Cancel']");
    }
    public By errorMessage = By.xpath("//div[@class='error message']");
    public By noteMessage = By.xpath("//div[@class='message']/li[contains(text(),'currently book')]");

    public WebElement getBtnCancel(String departStation, String arriveStation){
        return Constant.DRIVER.findElement(btnCancel(departStation, arriveStation));
    }
    public WebElement getTxtErrorMessage(){ return Constant.DRIVER.findElement(errorMessage); }
    public WebElement getTxtNoteMessage(){ return Constant.DRIVER.findElement(noteMessage); }

    public String getErrorMessage(){ return getTxtErrorMessage().getText(); }
    public String getNoteMessage(){ return getTxtNoteMessage().getText(); }

    public void cancelTicket(String departStation, String arriveStation){
        scrollDownByVisibleOfElement(getBtnCancel(departStation, arriveStation));
        getBtnCancel(departStation, arriveStation).click();
    }
    public void confirmCancelTicket(){
        Constant.DRIVER.switchTo().alert().accept();
        explicitWait(Constant.MEDIUM_WAIT, errorMessage);
    }
}
