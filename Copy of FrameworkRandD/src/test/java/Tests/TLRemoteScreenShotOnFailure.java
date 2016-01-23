package Tests;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import driverFactory.CustomRemoteWebDriver;
import driverFactory.CustomWebDriver;
import pages.BasePage;


public class TLRemoteScreenShotOnFailure extends TestListenerAdapter {
	//public CustomRemoteWebDriver driver;
	public CustomWebDriver driver = new CustomWebDriver();
	//public static ThreadLocal<CustomWebDriver> driver;
	@Override
	public void onTestFailure(final ITestResult tr) {
		driver.reportScreenShot();
		    //driver = (CustomRemoteWebDriver) tr.getTestContext().getAttribute ("driverKey");
		//driver = BasePage.getDriverInstance();
		//BasePage.reportScreenShot();
		//ThreadLocal<CustomWebDriver> driver = BasePage.getDriverInstance();
		//CustomRemoteWebDriver augmentedDriver = (CustomRemoteWebDriver) new Augmenter().augment((WebDriver) driver);
		//driver.reportScreenShot();
	  	//driver = BasePage.getDriverInstance();
	  	//driver = (ThreadLocal<WebDriver>) TLRemoteWebDriverManager.d;
		/*File scrFile =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		String destDir = "target/surefire-reports/screenshots";
		new File(destDir).mkdirs();
		String destFile = dateFormat.format(new Date()) + ".png";

        try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.setEscapeHtml(false);
		Reporter.log("Saved <a href=../screenshots/" + destFile + ">Screenshot</a>");*/
	}
}
