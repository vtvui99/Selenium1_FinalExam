package TestCases;

import Common.BaseTest;
import Common.DataGeneration;
import Common.Log;
import PageObjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FTTC01 extends BaseTest {
    HomePage homePage = new HomePage();
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    MyTicketPage myTicketPage = new MyTicketPage();

    @Test(dataProvider = "getData", description = "Manage Ticket displays remaining available number of booking.")
    public void FTTC01(Object[] data) throws InterruptedException {
        Log.info(FTTC01.class.getSimpleName());
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
        String arrayDate = data[2].toString();
        String departStation = data[3].toString();
        String arriveStation = data[4].toString();
        String seatType = data[5].toString();
        String ticketAmount = data[6].toString();
        bookTicketPage.bookTicketSeveralTimes(arrayDate,departStation, arriveStation, seatType, ticketAmount);
        Log.info("Book tickets");

        bookTicketPage.clickMyticketTab();
        int numberOfBookedTicket = bookTicketPage.numberOfBookedTicket(arrayDate, ticketAmount);
        String expectedNoteMessage = "You currently book " + numberOfBookedTicket + " tickets, you can book "+ (10 - numberOfBookedTicket) + " more.";
        String actualNoteMessage = myTicketPage.getNoteMessage();

        Assert.assertEquals(actualNoteMessage, expectedNoteMessage);
        Log.info("Compare note message");
    }
}
