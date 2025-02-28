package core.driver_wrapper.management;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import core.common.Constant;
import core.driver_wrapper.DriverType;

public class DriverManagement {
	
	private static final Logger Logger = Constant.createLogger(DriverManagement.class.getName());
	private static Map<String, WebDriver> map = new HashMap<String, WebDriver>();
	
	
	
	/**
	 * @author
	 * Creates the driver.
	 * @param type: driver type
	 * @param runningMode: mode to run
	 */
	public static void createDriver(DriverType type, String runningMode) {
		new Driver(type, runningMode);
	}
	
	
	/**
	 * @author
	 *Gets the thread id.
	 * @return the thread id
	 */
	protected static String getThreadId() {
		return String.valueOf(Thread.currentThread().getId());
	}
	
	/**
	 * @author
	 * Gets the current web driver by thread id.
	 * @return the driver
	 */
	public static WebDriver getDriver() {
		return map.get(getThreadId());
	}
	
	
	protected static void addDriver(WebDriver driver) {
		try {
			Logger.info("DriverManagement are adding driver on thread " + getThreadId());
			map.put(getThreadId(), driver);
		} catch (Exception e) {
			// TODO: handle exception
			Logger.severe(e.getMessage());
		}
		
	}
	

}
