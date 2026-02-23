package listeners;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import advanceReport.ExtentManager;
import base.BaseClass;
import genericUtilitis.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static ExtentReports ex_report = new ExtentManager().getExtentReportObject();
    private static ThreadLocal<ExtentTest> extent_test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getMethod().getMethodName() + " " + Arrays.toString(result.getParameters());
        extent_test.set(ex_report.createTest(name));
        extent_test.get().info("Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extent_test.get().pass("Test PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseClass.getDriver();
        String name = result.getMethod().getMethodName();

        extent_test.get().fail("Test FAILED");
        extent_test.get().fail(result.getThrowable());

        try {
            String path = ScreenshotUtil.takeScreenshot(driver, name);
            extent_test.get().addScreenCaptureFromPath(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ex_report.flush();
    }
}
