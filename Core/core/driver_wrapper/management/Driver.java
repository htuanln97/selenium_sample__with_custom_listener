package core.driver_wrapper.management;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.common.Constant;
import core.driver_wrapper.DriverType;

public class Driver extends BaseDriver{
	
	private static final Logger Logger = Constant.createLogger(Driver.class.getName());
	private static DesiredCapabilities capabilities;
	private WebDriver driver;
	
	/**
	 * @author: tuan.le
	 * Initialize a new driver.
	 * @param type of driver
	 * @param parallel: true or false
	 * @param hub: url hub
	 */
	public Driver(DriverType type, String runningMode) {
		try {
			Logger.info(String.format("Creating new %s driver", type.getValue()));
			switch (type.getValue().toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", Constant.CHROME_PATH);
				if(runningMode.equalsIgnoreCase("remote")) {
					capabilities = DesiredCapabilities.chrome();
					driver = new RemoteWebDriver(new URL(Constant.HUB_URL), capabilities);
				}else if (runningMode.equalsIgnoreCase("local")){
					driver = new ChromeDriver();
				}
				DriverManagement.addDriver(driver);
				
				break;
				
			case "firefox":
				System.setProperty("webdriver.gecko.driver", Constant.FIREFOX_PATH);
				if(runningMode.equalsIgnoreCase("remote")) {
					capabilities = DesiredCapabilities.firefox();
					driver = new RemoteWebDriver(new URL(Constant.HUB_URL), capabilities);
				}else if (runningMode.equalsIgnoreCase("local")) {
					driver = new FirefoxDriver();
				}
				DriverManagement.addDriver(driver);
				
				break;

			default:
				System.out.println(String.format("Invalid driver value => '%s'", type.getValue()));
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			Logger.severe(e.getMessage());
		}
		
	}
	
	
	/**
	 * @author
	 * Javascript executor.
	 * @return the javascript executor
	 */
	private static JavascriptExecutor getJsExecutor() {
		return ((JavascriptExecutor) getDriver());
	}

	/**
	 * @author 
	 *  Execute JS script.
	 * @param script: string of script
	 * @param args   the args
	 * @return the object
	 */
	public static Object executeScript(String script, Object... args) {
		return getJsExecutor().executeScript(script, args);
	}

	/**
	 * @author
	 * Scroll to up.
	 */
	public static void scrollToUp() {
		Logger.info(String.format("Scroll the driver %s to up", DriverManagement.getThreadId()));
		Object objHeight =  getJsExecutor().executeScript("return document.body.scrollHeight");
		int height = Integer.valueOf(objHeight.toString());
		int i= (height/10);
		while(i<=height) {
			String js = String.format("window.scrollBy(0,-%s)",String.valueOf(i),"");
			getJsExecutor().executeScript(js);
			waitForLoadingPage();
			i+=(height/10);
		}
	}
	
	/**
	 * @author
	 * Scroll till end.
	 */
	public static void scrollTillEnd() {
		Logger.info(String.format("Scroll the driver %s till end", DriverManagement.getThreadId()));
		Object objHeight =  getJsExecutor().executeScript("return document.body.scrollHeight");
		int height = Integer.valueOf(objHeight.toString());
		int i= (height/10);
		while(i<=height) {
			String js = String.format("window.scrollBy(0,%s)",String.valueOf(i));
			getJsExecutor().executeScript(js);
			waitForLoadingPage();
			Driver.delay(0.5D);
			i+=(height/10);
		}
	}

	/**
	 * @author
	 * Sets the implicitly wait.
	 * Default timeout could be found in core.common.Constant class
	 */
	public static void setImplicitlyWait() {
		setImplicitlyWait(Constant.TIMEOUT);
	}
	
	/**
	 * @author
	 * Sets the implicitly wait.
	 * @param timeOutInSecond the new implicitly wait
	 */
	public static void setImplicitlyWait(int timeOutInSecond) {
		Logger.info(String.format("Set driver [%s] implicitly wait [%s]s", DriverManagement.getThreadId(), timeOutInSecond));
		getDriver().manage().timeouts().implicitlyWait(timeOutInSecond, TimeUnit.SECONDS);
	}
	
	public static void waitForLoadingPage() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return Driver.executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(getDriver(), Constant.LONG_TIMEOUT);
        wait.until(pageLoadCondition);
    }
	
	public static void wait(int timeInSecond) {
		 try {
				Thread.sleep(timeInSecond * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void delay(double timeInSecond) {
		try {
			Thread.sleep((long) (timeInSecond * 1000.0D));
		} catch (Exception var3) {
			Logger.severe("An error occurred when delay: " + var3.getMessage());
		}
	}
	
	public static void switchToSpecificTab(int index) {
	    ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
	    getDriver().switchTo().window(tabs.get(index));
	}
	
}
