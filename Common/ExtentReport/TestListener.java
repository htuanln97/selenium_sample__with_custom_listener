package ExtentReport;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Common.Utilities;
import core.logger.CoreLogger;

public class TestListener implements ITestListener, IInvokedMethodListener {

	public static Map<String, ExtentTest> testSuite = new HashMap<String, ExtentTest>();
	private static final org.apache.logging.log4j.Logger log = CoreLogger.createLogger(TestListener.class);

	public void onStart(ITestContext context) {
		log.info("*** Test Suite " + context.getName() + " started ***");
		// Handle Report
		if (!ExtentTestManager.isTestExisted(context.getName())) {
			ExtentTest tmpSuite = ExtentTestManager.startTest(context.getName(), null);
			testSuite.put(context.getName(), tmpSuite);
		}
	}

	public void onTestStart(ITestResult result) {
		log.info(("*** Running test: " + result.getMethod().getMethodName() + " ..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName(), testSuite.get(result.getTestContext().getName()));
		ExtentTestManager.getTest().assignCategory(result.getTestContext().getName());
	}

	public void onFinish(ITestContext context) {
		log.info(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentReportManager.getInstance().flush();
	}

	public void onTestSuccess(ITestResult result) {
		ExtentTest info = ExtentTestManager.getTest();
		String message = String.format("<b>*** TEST EXECUTION COMPLETE - PASSED: %s </b>", result.getMethod().getMethodName());
		// Handle for ExtentReports
		info.log(Status.PASS, message);
		// Handle for TestNG
		Reporter.log(message);
	}

	public void onTestFailure(ITestResult result) {

		if (result.getThrowable() instanceof AssertionError) {
			ExtentTestManager.getTest().fail("*** TEST EXECUTION COMPLETE - FAILED: "
					+ result.getMethod().getMethodName() + " - " + result.getThrowable().getMessage());
		} else {
			// capture screenshot
			String screenshotFileName = UUID.randomUUID().toString();
			String screenshotFilePath = "";
			try {
				screenshotFilePath = Utilities.captureScreenShot(screenshotFileName,
						ExtentReportManager.getScreenshotFolder());
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// attach screenshots to report
			try {
				ExtentTestManager.getTest()
						.error("*** TEST EXECUTION COMPLETE - ERROR: " + result.getMethod().getMethodName() + " - "
								+ result.getThrowable().getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(screenshotFilePath).build());
			} catch (IOException e) {
				ExtentTestManager.getTest().error("*** TEST EXECUTION COMPLETE - ERROR: "
						+ result.getMethod().getMethodName() + " - " + result.getThrowable().getMessage());
				Logger.info("An exception occured while taking screenshot " + e.getCause());
			}
		}
		/*
		try {
			ExtentTest detailFailed = ExtentTestManager.getTest();

			if (result.getThrowable().toString().contains("Assert")) {
				String message = "<b>Assertion failed.</b>";

				// Handle for ExtentReports
				detailFailed.fail(message);

				// Handle for TestNG
				Reporter.log(message);
			} else {
				String message = "<b>Test failed by error.</b>";

				// Get screenshot
				String screenshotFileName = UUID.randomUUID().toString();
				String path = Utilities.takeScreenShot(screenshotFileName,
						ExtentReportManager.getScreenshotFolder());
				System.out.printf("file:///%s\n",path.replace('\\','/').replace(" ", "%20"));
				// Handle for ExtentReports
				detailFailed.error(message);
				detailFailed.error(result.getThrowable());
				detailFailed.addScreenCaptureFromPath(ExtentReportManager.getScreenshotFolder() + screenshotFileName + ".png");

				// Handle for TestNG
				Reporter.log(message);
				Reporter.log(result.getThrowable().getMessage());
//				Reporter.log(Common.screenshotURI(path));
			}

//			Logger.failedAssertion(result.getThrowable().toString());
//			System.out.printf("Folder report\nfile:///%s\n",ExtentTestManager.getCurrentReportFolderPath()
//					.replace('\\','/').replace(" ", "%20"));


		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

*/
	}

	public void onTestSkipped(ITestResult result) {
		Logger.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		Logger.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}
	
	public void afterInvocation(IInvokedMethod method, ITestResult testResult){
		if (testResult.getMethod().isTest()) {
			//Throw custom exception error if there is failed verification in test result
			if (testResult.getStatus() == ITestResult.SUCCESS) {
				if (ExtentTestManager.getTest().getStatus() == Status.FAIL) {
					Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
					Reporter.getCurrentTestResult().setThrowable(new AssertionError("Verification failed."));
				}
			}
		}
	}	
}