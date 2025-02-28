package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Enums.Railway.SeatType;
import Enums.Railway.Station;
import GuruWebMail.LoginPage;
import GuruWebMail.MailBoxPage;

public class ManageTicketTest extends TestBase {
	
	HomePage homePage = new HomePage();
	GeneralPage generalPage = new GeneralPage();
	private Railway.LoginPage loginPage;
	private LoginPage guruLoginPage = new LoginPage();
	private MailBoxPage guruMailBox;
	private RegisterPage registerPage;
	private BookTicketPage bookTicketPage;
	private BookSuccessPage bookSuccessPage;
	private MyTicketPage myTicketPage;
	
  @Test
  public void FTTC01() {
	  System.out.println("FTTC01 - User can filter 'Manage Ticket Table' with Depart Date");
	  
	  
	  //Pre-condition: Create and activate a new account
	  
	  //Generate account
	  
	  //Account for login to guru to activate
	  Account accountGuru = new Account(Constant.USERNAMEMAILGURU, Constant.PASSWORDMAILGURU);
	  //Account for create new
	  Account newAccount = new Account("test" + Utilities.getDateWithTimeMils() +"@gmail.com",
			  Constant.PASSWORD, Constant.PID);
	  
	  //Precondition: Create and activate a new account
	  //Create new account
	  homePage.open();
	  
	  registerPage = homePage.gotoRegisterPage();
	  
	  registerPage.createNewAccountSuccess(newAccount);
	  
	  //Activate account
	  Utilities.openNewTab(Constant.GURU_WEBMAIL_URL);
	  guruMailBox = guruLoginPage.login(accountGuru);
	  guruMailBox.clickMailContent(newAccount.getUserName(), "confirm");
	  guruMailBox.clickMailToActivate();
	  //Close new open tab
	  Utilities.closeCurrentTab();
	  Utilities.switchToNewTab();
	  Utilities.closeCurrentTab();
	  
	  //1. Navigate to QA Railway Website page
	  homePage.open();
	  
	  //2. Login with a valid account
	  loginPage = homePage.gotoLoginPage();
	  homePage = loginPage.loginSuccess(newAccount);
	  
	  //3 Book More Than 6 tickets with different Depart Dates
	  
	  //Go to book ticket page
	  bookTicketPage = homePage.gotoBookTicketPage();
	  
	  //Generate date for booking ticket
	  String dateNowPlus3 = Utilities.getDateNowWithDayPlus(Constant.FTIME_CURRENT,3);
	  String dateNowPlus4 = Utilities.getDateNowWithDayPlus(Constant.FTIME_CURRENT,4);
	  String dateNowPlus5 = Utilities.getDateNowWithDayPlus(Constant.FTIME_CURRENT,5);
	  String dateNowPlus6 = Utilities.getDateNowWithDayPlus(Constant.FTIME_CURRENT,6);
	  String dateNowPlus7 = Utilities.getDateNowWithDayPlus(Constant.FTIME_CURRENT,7);
	  String dateNowPlus8 = Utilities.getDateNowWithDayPlus(Constant.FTIME_CURRENT,8);
	  String dateNowPlus9 = Utilities.getDateNowWithDayPlus(Constant.FTIME_CURRENT,9);
	  
	  //Generate 6 tickets with different dates
	  Ticket ticket1 = new Ticket(dateNowPlus3, Station.PHAN_THIET.getStation(), Station.DA_NANG.getStation(),
				SeatType.HARD_BED.getSeatTypeName(), 1);
	  Ticket ticket2 = new Ticket(dateNowPlus4, Station.PHAN_THIET.getStation(), Station.DA_NANG.getStation(),
				SeatType.HARD_BED.getSeatTypeName(), 1);
	  Ticket ticket3 = new Ticket(dateNowPlus5, Station.PHAN_THIET.getStation(), Station.DA_NANG.getStation(),
				SeatType.HARD_BED.getSeatTypeName(), 1);
	  Ticket ticket4 = new Ticket(dateNowPlus6, Station.PHAN_THIET.getStation(), Station.DA_NANG.getStation(),
				SeatType.HARD_BED.getSeatTypeName(), 1);
	  Ticket ticket5 = new Ticket(dateNowPlus7, Station.PHAN_THIET.getStation(), Station.DA_NANG.getStation(),
				SeatType.HARD_BED.getSeatTypeName(), 1);
	  Ticket ticket6 = new Ticket(dateNowPlus8, Station.PHAN_THIET.getStation(), Station.DA_NANG.getStation(),
				SeatType.HARD_BED.getSeatTypeName(), 1);
	  Ticket ticket7 = new Ticket(dateNowPlus9, Station.PHAN_THIET.getStation(), Station.DA_NANG.getStation(),
				SeatType.HARD_BED.getSeatTypeName(), 1);
	  
	  //Book Ticket 1
	  bookSuccessPage = bookTicketPage.bookTicket(ticket1);
	  //Book Ticket 2
	  bookTicketPage = bookSuccessPage.gotoBookTicketPage();
	  bookSuccessPage = bookTicketPage.bookTicket(ticket2);
	  //Book Ticket 3
	  bookTicketPage = bookSuccessPage.gotoBookTicketPage();
	  bookSuccessPage = bookTicketPage.bookTicket(ticket3);
	  //Book Ticket 4
	  bookTicketPage = bookSuccessPage.gotoBookTicketPage();
	  bookSuccessPage = bookTicketPage.bookTicket(ticket4);
	  //Book Ticket 5
	  bookTicketPage = bookSuccessPage.gotoBookTicketPage();
	  bookSuccessPage = bookTicketPage.bookTicket(ticket5);
	  //Book Ticket 6
	  bookTicketPage = bookSuccessPage.gotoBookTicketPage();
	  bookSuccessPage = bookTicketPage.bookTicket(ticket6);
	  //Book Ticket 7
	  bookTicketPage = bookSuccessPage.gotoBookTicketPage();
	  bookSuccessPage = bookTicketPage.bookTicket(ticket7);
	  
	  //4. Click on 'My Ticket' tab
	  myTicketPage = bookSuccessPage.gotoMyTicketPage();
	  
	  //5. Input one of booked Depart Date 'Depart date' textbox
	  //6. Click 'Apply filter'
	  myTicketPage.searchFilterWithDepartDate(ticket1.getDepartDate());
	  
	  //Verify Point: 'Manage Ticket' table show correct ticket(s);
	  
	  
	  Assert.assertEquals(myTicketPage.getDepartDateInfo(), 
			  ticket1.getDepartDate(),
			  "Depart date should be: " +ticket1.getDepartDate());
	  
	  Assert.assertEquals(myTicketPage.getDepartFromInfo(), 
			  ticket1.getDepartFrom(),
			  "Depart From should be: " +ticket1.getDepartFrom());
	  
	  Assert.assertEquals(myTicketPage.getArriveAtInfo(), 
			  ticket1.getArriveAt(),
			  "Arrive At should be: " +ticket1.getArriveAt());
	  
	  Assert.assertEquals(myTicketPage.getSeatTypeInfo(), 
			  ticket1.getSeatType(),
			  "Seat Type should be: " +ticket1.getSeatType());
	  
	  Assert.assertEquals(myTicketPage.getAmountInfo(), 
			  ticket1.getTicketAmount(),
			  "Ticket Amount should be: " +ticket1.getTicketAmount());
	  
	  
  }
}
