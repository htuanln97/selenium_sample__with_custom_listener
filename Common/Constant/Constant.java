package Constant;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Constant {
	//Driver
	public static WebDriver WEBDRIVER; //this is not use in selenium lv2. I use frame work lv1 to build up to lv2.
	public static WebDriverWait WAIT;
	
	//Data
	public static final String TIKI_URL = "https://tiki.vn/";
	public static final String FOODY_URL = "https://www.foody.vn/";
	
	
	public static final String RAILWAY_URL = "http://192.168.171.251:8081/Page/HomePage.cshtml";
	public static final String GURU_WEBMAIL_URL = "http://mail.ges.guru/webmail";
	public static final String USERNAME = "qcauto@mailinator.com";
	public static final String PASSWORD = "123456789";
	public static final String PID = "0123456789";
	public static final String USERNAMEMAILGURU = "safe.railway@ges.guru";
	public static final String PASSWORDMAILGURU = "Logigear123!!";
	
	

	// Timeout variables
	public static final int VERY_LONG_TIME = 180;
	public static final int LONG_TIME = 120;
	public static final int NORMAL_TIME = 60;
	public static final int SHORT_TIME = 30;
	public static final int VERY_SHORT_TIME = 5;
	public static final int DISPOSE_TIME = 10;
	public static final int SLEEP_TIME = 3;
	public static final int LOADING_TIME = 2;
	public static final int DEFAULT_TIMEOUT = 30;
	
	// Date Format
	public final static String FTIME_SHORT_DETAIL = "yyyy-MM-dd HH:mm:ss.SSS";
	public final static String FTIME_V1 = "MMM d, yyyy";
	public final static String FTIME_V2 = "MMddyyyy";
	public final static String FTIME_V3 = "yyyy MMM d";
	public final static String FTIME_V4 = "dd-MM-yyyy";
	public final static String FTIME_V5 = "MMM dd, yyyy";
	public final static String FTIME_V6 = "MMMM d, yyyy";
	public final static String FTIME_CURRENT = "MM/dd/yyyy";


}
