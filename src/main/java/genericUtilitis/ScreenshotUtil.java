package genericUtilitis;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	public static String takeScreenshot(WebDriver driver, String testName) throws IOException {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);

	    String path = "reports/screenshots/"+testName+"_" + System.currentTimeMillis() + ".png";
	    File dest = new File(path);
	    FileUtils.copyFile(src, dest);

	    return path;
	}
}
