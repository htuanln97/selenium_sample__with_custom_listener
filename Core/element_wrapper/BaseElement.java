package element_wrapper;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.common.Constant;
import core.driver_wrapper.management.Driver;
import core.driver_wrapper.management.DriverManagement;


public class BaseElement {
	
	private static final Logger Logger = Constant.createLogger(BaseElement.class.getName());
	
	protected WebElement _element = null;
	protected List<WebElement> _elements = null;
	
	private By _byLocator;
	
	
	public BaseElement(By locator) {
		this._byLocator = locator;
	}

	public BaseElement(String xpath) {
		this._byLocator = By.xpath(xpath);
	}
	
	public BaseElement(WebElement elem) {
		this._element = elem;
	}
	
	protected By getLocator() {
		return this._byLocator;
	} 
	
	protected WebDriver getDriver() {
		return DriverManagement.getDriver();
	}
	
	protected WebElement getElement() {
		try {
			return getDriver().findElement(this.getLocator());
		} catch (Exception e) {
			return null;
		}
	}
	
	protected List<WebElement> getElements(){
		try {
			return getDriver().findElements(this.getLocator());
		} catch (Exception e) {
			return null;
		}
	}
	
	protected WebElement getElement(int timeOutInSeconds) {
		return waitForPresent(timeOutInSeconds);
	}
	
	protected List<WebElement> getElements(int timeOutInSeconds) {
		return waitForAllElementsPresent(timeOutInSeconds);
	}
	
	
	/*=========================Element-Waiter===========================================================================*/
	public WebElement waitForPresent(int timeOutInSeconds) {
		Logger.info(String.format("Wait for control %s to be present in DOM with timeOut %s", getLocator().toString(),
				timeOutInSeconds));
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			_element = wait.until(ExpectedConditions.presenceOfElementLocated(getLocator()));
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//			throw e;
		}
		return _element;
	}
	
	public List<WebElement> waitForAllElementsPresent(int timeOutInSeconds) {
		Logger.info(String.format("Wait for all controls %s to be present in DOM with timeOut %s", getLocator().toString(),
				timeOutInSeconds));
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			_elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocator()));
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//			throw e;
		}
		return _elements;
	}
	
	public WebElement waitForClickable(int timeOutInSeconds) {
		try {
			Logger.info(String.format("Wait for control %s to be clickabled with timeout: %s", getLocator().toString(), timeOutInSeconds));
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			_element = wait.until(ExpectedConditions.elementToBeClickable(getLocator()));
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//			throw e;
		}
		return _element;
	}
	
	public void waitForInvisibility(int timeOutInSeconds) {
		try {
			Logger.info(String.format("Wait for control %s invisibility with timeOut: %s", getLocator().toString(),
					timeOutInSeconds));
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(getLocator()));
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//			throw e;
		}
	}
	
	public WebElement waitForVisibility() {
		return waitForVisibility(Constant.TIMEOUT);
	}
	
	public WebElement waitForVisibility(int timeOutInSeconds) {
		try {
			Logger.info(String.format("Wait for control %s visibility with timeOut: %s", getLocator().toString(),
					timeOutInSeconds));
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			_element = wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator()));
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//			throw e; should not throw exception cause it will stop execute program
		}
		return _element;
	}
	
	public List<WebElement> waitForVisibilityOfAllElements(int timeOutInSeconds) {
		try {
			Logger.info(String.format("Wait for control %s of elements visibility with timeOut: %s", getLocator().toString(),
					timeOutInSeconds));
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			_elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator()));
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//			throw e;
		}
		return _elements;
	}
	
	public void waitForPositionNotChange() {
		ExpectedCondition<Boolean> positionNotChangeCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				if (getElement() != null) {
					WebElement element = getElement();
					if (element.getLocation() != null) {
						Point pOld = element.getLocation();
						Driver.delay(0.5D);
						Point pNew = element.getLocation();
						return pOld.equals(pNew);
					}
				}
				return false;
			}
		};
		WebDriverWait wait = new WebDriverWait(getDriver(), Constant.LONG_TIMEOUT);
		wait.until(positionNotChangeCondition);
	}
	
	public void handleStaleOfElement() {
		int count = 0;
		while (count < 2) {
			try {
				waitForVisibility(Constant.TIMEOUT);
				break;
			} catch (StaleElementReferenceException e) {
				Logger.warning(String.format("Attempting to wait for element: %s", getLocator().toString()));
				count ++;
			} catch (Exception error) {
				Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
				break;
			}
			
		}
	}
	
	public void handleStaleOfElements() {
		int count = 0;
		while (count < 5) {
			try {
				waitForVisibilityOfAllElements(Constant.TIMEOUT);
				break;
			} catch (StaleElementReferenceException e) {
				Logger.warning(String.format("Attempting to wait for element: %s", getLocator().toString()));
				count ++;
			} catch (Exception error) {
				Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
				break;
			}
			
		}
	}
	
	/*================================================================ Check ==================================================*/
	public boolean isElementEnable(int timeOutInSeconds) {
		try {
			Logger.info(String.format("Wait for control %s to be enable", getLocator().toString()));
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			_element = wait.until(ExpectedConditions.elementToBeClickable(getLocator()));
			return _element.isEnabled();
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
		}
		return false;
	}
	
	public boolean isDisplayed() {
		try {
			WebElement e = getElement();
			if(e != null) {
				return e.isDisplayed();
			}
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
		}
		return false;
	}
	
	public boolean isDisplayed(int timeOutInSeconds) {
		try {
			WebElement e = getElement(timeOutInSeconds);
			if(e != null) {
				return e.isDisplayed();
			}
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
		}
		return false;
	}
	
	public boolean isSelected(int timeOutInSeconds) {
		try {
			return getElement().isSelected();
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			return false;
		}
	}
	
	
}
