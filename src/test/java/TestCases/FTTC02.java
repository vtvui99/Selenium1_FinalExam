package TestCases;

import Common.BaseTest;
import Common.DataGeneration;
import Common.Log;
import PageObjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FTTC02 extends BaseTest {
    HomePage homePage =  new HomePage();
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    MyTicketPage myTicketPage = new MyTicketPage();

    @Test(dataProvider = "getData", description = "User can cancel ticket.")
    public void FTTC02(Object[] data) throws InterruptedException {
        Log.info(FTTC02.class.getSimpleName());
        String username = DataGeneration.generateRandomEmail(data[0].toString());
        String password = data[1].toString();
        String pid = DataGeneration.generateString();

        homePage.clickRegisterTab();
        registerPage.registerAccount(username, password, pid);
        Log.info("Register an account");

        registerPage.clickLoginTab();
        loginPage.loginToSystem(username, password);
        Log.info("Login with registered account");

        loginPage.clickBookTicketTab();
        String date = data[2].toString();
        String departStation = data[3].toString();
        String arriveStation = data[4].toString();
        String seatType = data[5].toString();
        String ticketAmount = data[6].toString();
        bookTicketPage.bookTicket(date, departStation, arriveStation, seatType, ticketAmount);
        Log.info("Book tickets");

        bookTicketPage.clickMyticketTab();
        myTicketPage.cancelTicket(departStation, arriveStation);
        Log.info("Cancel tickets");
        myTicketPage.confirmCancelTicket();
        Log.info("Confirm cancel tickets");

        String expectedErrorMessage = "Sorry, can't find any results that match your filters.\nPlease change the filters and try again.";
        String expectedNoteMessage = "You currently book 0 ticket, you can book 10 more.";
        String actualErrorMessage = myTicketPage.getErrorMessage();
        String actualNoteMessage = myTicketPage.getNoteMessage();

        Assert.assertTrue(actualErrorMessage.equals(expectedErrorMessage) & actualNoteMessage.equals(expectedNoteMessage));
        Log.info("Compare message");
    }
}
