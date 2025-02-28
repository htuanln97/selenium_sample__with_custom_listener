package ExtentReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

import Common.Utilities;
import core.driver_wrapper.management.Driver;
import core.driver_wrapper.management.DriverManagement;
import core.logger.CoreLogger;
import org.testng.asserts.SoftAssert;

public class Logger {
	private static String methodName;
	private static String className;
	private static List<String> currentLogs = new ArrayList<>();
	protected static final org.apache.logging.log4j.Logger log = CoreLogger.createLogger(Logger.class);
	
	private static void saveLog(String message) {
		String currentMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		String currentClass = Thread.currentThread().getStackTrace()[3].getClassName();
		if (!currentMethod.equals(methodName)
				|| (currentMethod.equals(methodName) && !currentClass.equals(className))) {
			currentLogs.clear();
		}
		methodName = currentMethod;
		className = currentClass;
		currentLogs.add(message);
	}

	public static List<String> getCurrentLogs() {
		return currentLogs;
	}

	public static void info(String message, Integer levelTrace) {
		String sStackTrace = levelTrace == null ? ""
				: " || [" + Thread.currentThread().getStackTrace()[2 + levelTrace].toString() + "]";
		saveLog(message);
		log.info(message + sStackTrace);
		Reporter.log("<b>INFO: </b>" + message);
		try {
			ExtentTestManager.getTest().log(Status.INFO, "<span style=word-break:break-word>" + message + "</span>");
		} catch (NullPointerException e) {
			log.warn("getTest - it seems that we call logger outside of test method: " + e.getMessage());
		}
	}

	public static void debug(String message) {
		log.debug(message + " || [" + Thread.currentThread().getStackTrace()[2].toString() + "]");
		saveLog(message);
		Reporter.log("<b>DEBUG: </b>" + message);
		if (ExtentTestManager.getTest() != null)
			ExtentTestManager.getTest().log(Status.DEBUG, "<span style=word-break:break-word>" + message + "</span>");
	}

	public static void debug(String message, Integer levelTrace) {
		String sStackTrace = levelTrace == null ? ""
				: " || [" + Thread.currentThread().getStackTrace()[2 + levelTrace].toString() + "]";
		saveLog(message);
		Reporter.log("<b>DEBUG: </b>" + message);
		if (ExtentTestManager.getTest() != null)
			ExtentTestManager.getTest().log(Status.DEBUG, "<span style=word-break:break-word>" + message + "</span>");
	}

	public static void bug(String bugId, String bugLink) {
		String bugInfo = String.format("The bug %s-%s is added", bugId, bugLink);
		log.error(bugInfo + "\n" + Thread.currentThread().getStackTrace()[2].toString());
		saveLog(bugInfo);
		String msg = "<a target=\"_blank\" href=\"" + bugLink
				+ "\" style=\"color:#DF0101;font-size:14px;word-break:break-word;\">" + bugInfo + "</a>";
		Reporter.log(msg);
		if (ExtentTestManager.getTest() != null)
			ExtentTestManager.getTest().log(Status.WARNING, msg);
	}

	public static void warning(String message, Integer levelTrace) {
		String sStackTrace = levelTrace == null ? ""
				: " || [" + Thread.currentThread().getStackTrace()[2 + levelTrace].toString() + "]";
		log.info("WARNING: " + message + sStackTrace);
		saveLog("WARNING: " + message);
		message = "<b style=\"color: darkorange;word-break:break-word;\"><i>WARNING: </i>" + message + "</b>";
		Reporter.log(message);
		if (ExtentTestManager.getTest() != null)
			ExtentTestManager.getTest().log(Status.WARNING, message);
	}

	public static void verify(String message, Integer levelTrace) {
		String sStackTrace = levelTrace == null ? ""
				: " || [" + Thread.currentThread().getStackTrace()[2 + levelTrace].toString() + "]";
		log.info("VERIFY POINT: " + message + sStackTrace);
		saveLog("VERIFY POINT: " + message);
		message = "<b style=\"color: blue;word-break:break-word;\"><i style=\"color: #ff8000\">VERIFY POINT: </i>"
				+ message + "</b>";
		Reporter.log(message);
		if (ExtentTestManager.getTest() != null)
			ExtentTestManager.getTest().log(Status.INFO, message);
	}

	public static void pass(String message, Integer levelTrace) {
		String sStackTrace = levelTrace == null ? ""
				: " || [" + Thread.currentThread().getStackTrace()[2 + levelTrace].toString() + "]";
		log.info("Verification passed: " + message + sStackTrace);
		saveLog("Verification passed: " + message);
		message = "<b style=\"word-break:break-word;\"><i style=\"color: #00af00\">Verification passed: </i><i>"
				+ message + "</i></b>";
		Reporter.log(message);
		if (ExtentTestManager.getTest() != null)
			ExtentTestManager.getTest().log(Status.PASS, message);
	}
//------------------------------------------------------------------------------------
	public static void passedAssertion(String message) {
		message = "<b style=\"color: blue;word-break:break-word;\"><i style=\"color: #00af00\">" + message
				+ " </i></b>";
		Reporter.log(message);
		ExtentTestManager.getTest().log(Status.PASS, message);
	}

	public static void failedAssertion(String message) {
		message = "<b style=\"color: blue;word-break:break-word;\"><i style=\"color: red\">" + message + " </i></b>";
		Reporter.log(message);
		ExtentTestManager.getTest().log(Status.FAIL, message);
	}

	// Set level
	public static void info(String message) {
		info(message, 1);
	}

	public static void warning(String message) {
		warning(message, 1);
	}

	public static void verify(String message) {
		verify(message, 1);
	}

	public static void pass(String message) {
		pass(message, 1);
	}
	
	
	//---------------------------- Setup Fail
    public static void fail(String message, Throwable throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        ArrayList<StackTraceElement> newStackTrace = new ArrayList<>();
        for (StackTraceElement ele:
                stackTrace) {
            String classname = ele.getClassName();
            if (!classname.contains("org.testng.")
                    && !classname.contains("sun.reflset")
                    && !classname.contains(Logger.class.getName())
                    && !classname.contains(SoftAssert.class.getName())
                    && !ele.getMethodName().contains("assert")) {
                newStackTrace.add(ele);
            }
        }
        throwable.setStackTrace(newStackTrace.toArray(new StackTraceElement[newStackTrace.size()]));

        log.warn("Verification failed: " + message, throwable);
        try {
            log.debug(DriverManagement.getDriver().getCurrentUrl());
        } catch (Exception e ){}

        saveLog("Verification failed: " + message);
        message = "<b style=\"word-break:break-word;\"><i style=\"color: red\">Verification failed: </i><i>" + message + "</i></b>";

        // Get screenshot
        String screenshotFileName = UUID.randomUUID().toString();
        //String path = Utilities.captureScreenshot(screenshotFileName, ExtentReportManager.getScreenshotFolder());
        String path = "";
		try {
			path = Utilities.captureScreenShot(screenshotFileName,
					ExtentReportManager.getScreenshotFolder());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        // Handle for TestNG
        Reporter.log(message);
//        Reporter.log(Common.screenshotURI(path));

        // Handle for ExtentReports
        MediaEntityModelProvider screenshot = null;
        try {
            screenshot = MediaEntityBuilder.createScreenCaptureFromPath("screenshots\\" + screenshotFileName + ".png").build();
            log.info("file:///" + path.replace('\\','/').replace(" ", "%20") );
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExtentTestManager.getTest().log(Status.FAIL, message, screenshot);
    }
}