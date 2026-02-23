package advanceReport;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	public ExtentReports getExtentReportObject() {
		
		String path = "reports/extentReport.html";
		ExtentSparkReporter ex_spark = new ExtentSparkReporter(path);
		ExtentReports ex_report = new ExtentReports();
		ex_report.attachReporter(ex_spark);
		return ex_report;
	}
}
