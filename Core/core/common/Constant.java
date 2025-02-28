package core.common;

import java.util.logging.Logger;

import Common.Utilities;

public class Constant {

	public static final int TIMEOUT = 30;
	public static final int SHORT_TIMEOUT = 5;
	public static final int VERY_SHORT_TIMEOUT = 3;
	public static final int LONG_TIMEOUT = 60;
	
	public static final String CHROME_PATH = Utilities.getProjectPath() + "\\Executables\\chromedriver.exe";
	public static final String FIREFOX_PATH = Utilities.getProjectPath() + "\\Executables\\geckodriver.exe";
	
	public static String HUB_URL = "http://localhost:4445/wd/hub";
	
	static {
		String path = System.getProperty("user.dir") + "\\Core\\core\\common\\logger.properties";
		System.setProperty("java.util.logging.config.file", path);
	}

	/**
	 * @author tuan.le
	 * 
	 * Creates the logger.
	 *
	 * @param className the class name
	 * @return the logger
	 */
	public static final Logger createLogger(String className) {
		return Logger.getLogger(className);
	}
}
