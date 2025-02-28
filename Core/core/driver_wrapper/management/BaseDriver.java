package core.driver_wrapper.management;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.common.Constant;

class BaseDriver implements WebDriver{
	
	protected static final Logger Logger = Constant.createLogger(BaseDriver.class.getName());
	

	/**
	 * @author
	 * Gets the driver from the thread by its id.
	 * @return the driver
	 */
	protected static WebDriver getDriver() {
		return DriverManagement.getDriver();
	}
	
	public void get(String url) {
		Logger.info(String.format("Load a new web page for the driver %s", DriverManagement.getThreadId()));
		getDriver().get(url);
	}
	
	public String getCurrentUrl() {
		Logger.info(String.format("Get current url of the driver %s", DriverManagement.getThreadId()));
		return getDriver().getCurrentUrl();
	}
	
	public String getTitle() {
		Logger.info(String.format("Get current title of the driver %s", DriverManagement.getThreadId()));
		return getDriver().getTitle();
	}
	
	@Deprecated
	public List<WebElement> findElements(By by) {
		throw new UnsupportedOperationException("This method is not supported");
	}

	@Deprecated
	public WebElement findElement(By by) {
		throw new UnsupportedOperationException("This method is not supported");
	}

	public String getPageSource() {
		Logger.info(String.format("Get source of the last load page of the driver %s", DriverManagement.getThreadId()));
		return getDriver().getPageSource();
	}

	public void close() {
		Logger.info(String.format("Close the current window of the driver %s", DriverManagement.getThreadId()));
		getDriver().close();
	}

	public void quit() {
		Logger.info(String.format("Quit the driver %s", DriverManagement.getThreadId()));
		getDriver().quit();
	}

	@Deprecated
	public Set<String> getWindowHandles() {
		throw new UnsupportedOperationException("This method is not supported");
	}

	@Deprecated
	public String getWindowHandle() {
		throw new UnsupportedOperationException("This method is not supported");
	}

	public TargetLocator switchTo() {
		Logger.info(String.format("Send commands to the different frame or window of the driver %s",
				DriverManagement.getThreadId()));
		return getDriver().switchTo();
	}

	public Navigation navigate() {
		Logger.info(String.format("Navigate to a given url of the driver %s", DriverManagement.getThreadId()));
		return getDriver().navigate();
	}

	public Options manage() {
		Logger.info(String.format("Get the option interface of the driver %s", DriverManagement.getThreadId()));
		return getDriver().manage();
	}

}
