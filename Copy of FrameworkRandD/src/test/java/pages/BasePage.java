package pages;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import loggerFactory.TestLogger;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;

import utilities.GetPropVals;
import driverFactory.CustomRemoteWebDriver;
import driverFactory.CustomWebDriver;

public class BasePage {

	private static By signOutLocator = By.linkText("(Sign out)");
	private By progressPopLocator = By.id("igProgress");
	private By riskTblLocator = By.id("tblRisk");
	private By riskAddrTdLocator = By.xpath("//table[@id='tblRisk']//td[.='<val>']");
	private String riskAddrLocatorString = "//table[@id='tblRisk']//td[.='<val> US']";
	private By nextBtnLocator = By.xpath("//div[@class='igButtonDiv']/button[@class='igFlowNext'][1]");
	private By flowEditBtnLocator = By.xpath("//button[@class='igFlowEdit']");
	private By flowApplyBtnLocator = By.xpath("//div[@class='igButtonDiv']/button[@class='igFlowApply'][1]");
	private By notifierRecordLocator = By.xpath("//*[@id='SubRolesForTable' and contains(.,'Notifier')]");

	private static GetPropVals getPropVal = new GetPropVals();
	public static String hubAddrs = getPropVal.getPropValue("SeleniumGRIDUrl");
	private By financialsMenuLocator = By.xpath("//span[.='Financials']");
	private By manageReservesLinkLocator = By.linkText("Manage reserves");
	private By manageSIUReferralUpdateScoreLinkLocator = By.linkText("Manage SIU referral/update score");
	private By spotlightLinkLocator = By.linkText("Spotlight");
	private By functionsMenuLocator = By.xpath("//span[.='Functions']");
	private By incidentLinkLocator = By.linkText("Incident");
	private By partiesMenuLocator = By.xpath("//span[.='Parties']");
	private By SIUMenuLocator = By.xpath("//span[.='SIU']");
	private By partyListLinkLocator = By.linkText("Party list");
	private By logPaymentLinkLocator = By.linkText("Log payment");
	private String collapsedMenuLocatorString = "//div[@class='collapsed']//span[.='<menu>']";
	private static By errorElementLocator = By.xpath("//div[@id='igErrorPanel']");
	private By claimSummaryLinkLocator = By.linkText("Claim summary");
	
	public void writeStepToTestNGReport(String stepText) {
		TestLogger.log("<p><font face='verdana' color='BlueViolet'>=> " + stepText + "</font>");
		//Reporter.log("<p>==> <font face='verdana' color='BlueViolet'>" + stepText + "</font>");
		//BlueViolet 
	}
	public boolean waitUntilElementExists(By waitForElementLocator, int secondsToWait) {
		int cntr = 0;
		while (!(driver.get().findElements(signOutLocator).size() > 0)) {
			sleep(1);
			cntr++;
			if (cntr > secondsToWait) {
				break;
			}
		}
		if (driver.get().findElements(signOutLocator).size() > 0) {
			return true;
		} else {
			TestLogger.log("F", "<p><font face='verdana' color='red'>Element failed to load with in " + Integer.toString(secondsToWait) + " seconds.</font>");
			//Reporter.log("<p> ==> Element failed to load with in " + Integer.toString(secondsToWait) + " seconds.");
			driver.get().reportScreenShot();
			return false;
		}
	}
	
	public void expandFinancialsMenu() {
		writeStepToTestNGReport("Expand Financials menu");
		String menuName = "Financials";
		if (driver.get().findElements(By.xpath(collapsedMenuLocatorString.replace("<menu>", menuName))).size() > 0) {
			//if (existsElement(By.xpath(collapsedMenuLocatorString.replace("<menu>", menuName)))) {
			driver.get().findElement(financialsMenuLocator).click();
		}
	}

	public void expandFunctionsMenu() {
		writeStepToTestNGReport("Expand Functions menu");
		String menuName = "Functions";
		if (driver.get().findElements(By.xpath(collapsedMenuLocatorString.replace("<menu>", menuName))).size() > 0) {
			//if (existsElement(By.xpath(collapsedMenuLocatorString.replace("<menu>", menuName)))) {
			driver.get().findElement(functionsMenuLocator).click();
		}
	}

	public void expandPartiesMenu() {
		writeStepToTestNGReport("Expand Parties menu");
		String menuName = "Parties";
		if (driver.get().findElements(By.xpath(collapsedMenuLocatorString.replace("<menu>", menuName))).size() > 0) {
			//if (existsElement(By.xpath(collapsedMenuLocatorString.replace("<menu>", menuName)))) {
			driver.get().findElement(partiesMenuLocator).click();
		}
	}
	
	public void expandSIUMenu() {
		writeStepToTestNGReport("Expand SIU menu");
		String menuName = "SIU";
		if (driver.get().findElements(By.xpath(collapsedMenuLocatorString.replace("<menu>", menuName))).size() > 0) {
			driver.get().findElement(SIUMenuLocator).click();
		}
	}

	public void clickManageSIUReferralUpdateScoreLink() {
		writeStepToTestNGReport("Click Manage SIU Referral Update Score link");
		expandSIUMenu();
		driver.get().findElement(manageSIUReferralUpdateScoreLinkLocator).click();
		progressSync();
	}
	
	public void clickSpotlightLink() {
		writeStepToTestNGReport("Click Spotlight link");
		expandSIUMenu();
		driver.get().findElement(spotlightLinkLocator).click();
		progressSync();
	}
	
	public void clickManageReservesLink() {
		writeStepToTestNGReport("Click Manage Reserves link");
		expandFinancialsMenu();
		driver.get().findElement(manageReservesLinkLocator).click();
		progressSync();
	}

	public void clickIncidentLink() {
		writeStepToTestNGReport("Click Incident link");
		expandFunctionsMenu();
		driver.get().findElement(incidentLinkLocator).click();
		progressSync();
	}

	public void clickPartyListLink() {
		writeStepToTestNGReport("Click Party List link");
		expandPartiesMenu();
		driver.get().findElement(partyListLinkLocator).click();
		progressSync();
	}

	public void clickLogPaymentLink() {
		writeStepToTestNGReport("Click Log Payment link");
		expandFinancialsMenu();
		driver.get().findElement(logPaymentLinkLocator).click();
		progressSync();
	}

	public void selectNotifierRecord() {
		writeStepToTestNGReport("Select notifier record");
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(notifierRecordLocator));
		driver.get().findElement(notifierRecordLocator).click();
		progressSync();
	}

	//static WebDriver driver;
	public static ThreadLocal<CustomWebDriver> driver = new ThreadLocal<CustomWebDriver>();//wowuc
	//public final static ThreadLocal<Logger> logger = new ThreadLocal<Logger>();

	//public ThreadLocalPrintStream tLclPrntStrm = new ThreadLocalPrintStream(new PrintStream());
	//	public static ThreadLocal<WebDriver> driver = null; //wowc

	public static void instantiateDriver(String testClassName) throws MalformedURLException {
		//driver = new CustomWebDriver();
		//driver = (WebDriver) BaseTest.driver;//new CustomWebDriver();
		driver.set(new CustomWebDriver(new URL(hubAddrs), testClassName));//wowuc
		//logger.set(Logger.getLogger(testClassName));
		//logger.get().setLevel(new Level().ALL);
		//logger.get().info("webdriver created");
		//		driver.set((CustomWebDriver) ThreadGuard.protect(new CustomWebDriver(new URL(hubAddrs), DesiredCapabilities.firefox())));//wowc
	}
	
	public  void  setUp (final ITestContext context)  {
	    context.setAttribute ("driverKey", driver);
	}

	public static ThreadLocal<CustomWebDriver> getDriverInstance() {
		return driver;
	}

	public void logout() {
		writeStepToTestNGReport("Click Log Out link");
		driver.get().findElement(signOutLocator).click();
	}

	public void progressSync() {
		try {
			sleep(2);
			if (driver.get().findElements(progressPopLocator).size() > 0) {
				WebDriverWait wait = new WebDriverWait(driver.get(), 180);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(progressPopLocator));
			}
		} catch (WebDriverException wde) {
			//Auto-generated catch block
			wde.printStackTrace();
			driver.get().reportScreenShot();
		}
	}

	public void selectRiskAddressRecord(String addr) {
		writeStepToTestNGReport("Select risk address record");
		driver.get().findElement(By.xpath(riskAddrLocatorString.replace("<val>", addr))).click();
		progressSync();
	}

	public void clickFlowNext() {
		writeStepToTestNGReport("Click Flow Next button");
		driver.get().findElement(nextBtnLocator).click();
		progressSync();
	}

	public void clickFlowEdit() {
		writeStepToTestNGReport("Click Flow Edit(Pencil) button");
		boolean waitToLoad = waitUntilElementExists(flowEditBtnLocator, 30);
		if (waitToLoad) {
			WebDriverWait wait = new WebDriverWait(driver.get(), 30);
			wait.until(ExpectedConditions.elementToBeClickable(flowEditBtnLocator));
		}
		driver.get().findElement(flowEditBtnLocator).click();
		progressSync();
	}

	public void clickFlowApply() {
		writeStepToTestNGReport("Click Flow Apply button");
		driver.get().findElement(flowApplyBtnLocator).click();
		progressSync();
	}
	
	public void clickClaimSummary() {
		writeStepToTestNGReport("Click Claim Summary link");
		driver.get().findElement(claimSummaryLinkLocator).click();
	}
	
	public static void quitDriver(boolean resultStatus) {
		if (driver != null) {
			driver.get().quit();
			driver.set(null);
		}
	}

	public static boolean existsElement(By elementLocator) {
		try {
			driver.get().findElement(elementLocator);
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	public static void checkAndReportAppError() {
		if (existsElement(errorElementLocator)) {
			String errText = driver.get().findElement(errorElementLocator).getText();
			if (errText.trim().length() > 0) {
				if (errText.length() > 500) {
					errText = errText.substring(0, 499);
				}
				TestLogger.log("F", "<p><font face='verdana' color='red'>Application Error: </font><p><font face='verdana' color='red'>" + errText + "</font>");
				//Reporter.log("<p><font face='verdana' color='red'>Application Error: </font><p><font face='verdana' color='red'>" + errText + "</font>");
			}
		}
	}

	public boolean isAlertPresent() { 
		writeStepToTestNGReport("Check if Alert dialog is present");
		try 
		{ 
			driver.get().switchTo().alert(); 
			return true; 
		}   // try 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		}   // catch 
	} 

	public void acceptAlert() {
		writeStepToTestNGReport("Click OK on Alert dialog");
		try {
			if (isAlertPresent()) {
				Alert alert = driver.get().switchTo().alert();
				alert.accept();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void reportScreenShot() {
		CustomWebDriver augmentedDriver = (CustomWebDriver) new Augmenter().augment((WebDriver) driver);
		File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
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
		//Reporter.log(destFile + " - Screenshot");
		//Reporter.log("<p>Screenshot saved - <a href='../ws/target/surefire-reports/screenshots/"  + destFile + "' hight='100' width='100'/> </a>");
		//Reporter.log("<p>Screenshot saved - <a href=../ws/target/surefire-reports/screenshots/" + destFile + ">Screenshot</a>");
		TestLogger.log("<p><font face='verdana' color='red'>ERROR SCREENSHOT:" + "<br><br></font><a href=./screenshots/" + destFile + " target='_blank'><img src='./screenshots/" + destFile + "' alt='Error Snapshot' style='width:304px;height:228px'></a>");
		//Reporter.log("<p><font face='verdana' color='red'>ERROR SCREENSHOT:" + "<br><br></font><a href=./screenshots/" + destFile + " target='_blank'><img src='./screenshots/" + destFile + "' alt='Error Snapshot' style='width:304px;height:228px'></a>");
		//Reporter.log("<p><font face='verdana' color='red'>SCREENSHOT SAVED - " + "'.</font><a href=./screenshots/" + destFile + " target='_blank'>click here</a>");
	}
}