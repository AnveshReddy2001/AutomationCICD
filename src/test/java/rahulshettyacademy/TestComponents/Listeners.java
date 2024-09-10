package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test started: " + result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed: " + result.getName());
		extentTest.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test failed: " + result.getName());

		extentTest.get().fail(result.getThrowable());

		Object testClass = result.getInstance();

		if (testClass instanceof BaseTest) {
			BaseTest baseTest = (BaseTest) testClass;
			WebDriver driver = baseTest.getDriver();

			if (driver != null) {

				try {
					baseTest.getScreenshot(result.getMethod().getMethodName());
				} catch (IOException e) {

					e.printStackTrace();
				}
				System.out.println("Screenshot taken for failed test: " + result.getMethod().getMethodName());
			} else {
				System.out.println(
						"WebDriver is null, cannot take a screenshot for: " + result.getMethod().getMethodName());
			}
			String filePath = System.getProperty("user.dir") + "/reports/" + result.getMethod().getMethodName()
					+ ".png";
			;
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test skipped: " + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test finished: " + context.getName());
		extent.flush();
	}
}
