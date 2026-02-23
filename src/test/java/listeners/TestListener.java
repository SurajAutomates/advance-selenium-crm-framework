package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import advanceReport.ExtentManager;
import base.BaseClass;
import genericUtilitis.ScreenshotUtil;

public class TestListener implements ITestListener{

	ExtentReports ex_report = new ExtentManager().getExtentReportObject();
	ExtentTest extent_test;
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("test start");
		String name = result.getMethod().getMethodName();
		extent_test = ex_report.createTest(name);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("test pass");
		extent_test.pass("Test PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String name = result.getMethod().getMethodName();
		WebDriver driver = BaseClass.getDriver();
		extent_test.fail("Test FAILED : "+result.getThrowable());
		String path = null;
		try {
			 path = ScreenshotUtil.takeScreenshot(driver, name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (path!=null) {
			extent_test.addScreenCaptureFromPath(path);
		}
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("suite start");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("suite end");
		ex_report.flush();
	}
	
}
