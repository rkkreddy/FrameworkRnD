package pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.Spring;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import utilities.DBUtility;
import utilities.XLUtility;

public class SearchPage extends BasePage {
	
	private By searchByDdlLocator = By.id("CLM_SC_099_SearchBy");
    private By policyNoLocator = By.id("CLM_SC_099_PolicyNumber");
    private By searchBtnLocator = By.xpath("//div[@class='igButtonDiv']/button[.='Search'][1]");
    private By plcyActionDdlLocator = By.id("selPolicyAction");
    private By lossDateFldLocator = By.id("CLM_SC_099_IncidentDate_3");
    private By goBtnLocator = By.xpath("//input[@value='Go']");
    private By addClaimLocator = By.id("dupeClaimAdd");
    private By riskAddressWithCommasLocator = By.xpath("//td[@id='DESCRIPTION']");
    
    public Boolean isSearchByDisplayed() {
    	writeStepToTestNGReport("Check if Search By dropdown is displayed");
        //return driver.get().findElement(searchByDdlLocator).isDisplayed();
        return (waitUntilElementExists(searchByDdlLocator, 60));
    }

	public void selectValueFromSearchBy(String schBy) {
		writeStepToTestNGReport("Select value from Search By dropdown");
		new Select(driver.get().findElement(searchByDdlLocator)).selectByVisibleText(schBy);
		
	}
	
	public String getPolicyNumber(String policyVal) {
		writeStepToTestNGReport("Get a valid policy number");
		String rtnVal = "";
		if (policyVal.trim().equalsIgnoreCase("RANDOM")) {
			String rndPolicyNumQueryString = XLUtility.getCellValBasedOnOtherCellVal("./data/SQLQueries.xlsx", "Queries", "Name", "RandomPolicyNumber", "Query");
			//writeStepToTestNGReport("rndPolicyNumQueryString: " + rndPolicyNumQueryString);
			String horizonValidPolicyQuery = XLUtility.getCellValBasedOnOtherCellVal("./data/SQLQueries.xlsx", "Queries", "Name", "HorizonValidPolicy", "Query");
			//writeStepToTestNGReport("horizonValidPolicyQuery: " + horizonValidPolicyQuery);
			rtnVal = DBUtility.getRandomPolicyFromDbAfterHorizonSearch(rndPolicyNumQueryString, horizonValidPolicyQuery, "HSPOLICYNUMBER");
			writeStepToTestNGReport("Returned policy number: " + rtnVal);
			//rtnVal = (String) DBUtility.getRandomRowValFromDB("CMS", rndPolicyNumQueryString, "HSPOLICYNUMBER");
		} else {
			rtnVal = policyVal;
		}
		return rtnVal;
	}
	public void enterPolicyNumber(String policyNum) {
		writeStepToTestNGReport("Enter policy number");
		driver.get().findElement(policyNoLocator).sendKeys(policyNum);
	}

	public void clickSearchButton() {
		writeStepToTestNGReport("Click Search button");
		driver.get().findElement(searchBtnLocator).click();
		progressSync();
	}

	public void selectPolicyRecord(String policyNum) {
		writeStepToTestNGReport("Select policy record");
		driver.get().findElement(By.id(policyNum)).click();
		progressSync();
	}

	public void selectPolicyAction(String plcyAction) {
		writeStepToTestNGReport("Select policy action");
		new Select(driver.get().findElement(plcyActionDdlLocator)).selectByVisibleText(plcyAction);
		
	}

	public String getLossDate(String lsDate) {
		writeStepToTestNGReport("Get loss date");
		
		Date lossDate = new Date();
		Date lDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
	        if (lsDate.trim().toUpperCase().equals("TODAY")) {
	        	lsDate = sdf.format(lossDate);
	        } else {
	        	
	        	lDate = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(lsDate);
	        	lsDate = sdf.format(lDate);
	        }
		} catch (Exception e) {
			System.out.println("Passed string cannt be formatted to date");
			e.printStackTrace();
		}
        return lsDate.toString();
	}

	public void enterLossDate(String lsDate) {
		writeStepToTestNGReport("Enter loss date");
		driver.get().findElement(lossDateFldLocator).sendKeys(lsDate);
		progressSync();
	}

	public void clickGo() {
		writeStepToTestNGReport("Click Go button");
		driver.get().findElement(goBtnLocator).click();
		progressSync();
		if (driver.get().findElements(goBtnLocator).size() > 0) {
			driver.get().findElement(goBtnLocator).click();
			progressSync();
		}
		checkAndReportAppError();
	}

	public void clickAddClaimIfDisplayed() {
		writeStepToTestNGReport("Click Add Claim button if displayed");
		if (driver.get().findElements(addClaimLocator).size() > 0)
            driver.get().findElement(addClaimLocator).click();
		progressSync();
	}
	
	public String getRiskAddressWithCommas() {
		String rtnAddress = "";
		try {
			rtnAddress = driver.get().findElement(riskAddressWithCommasLocator).getText();
		} catch (NoSuchElementException nsee) {
			nsee.printStackTrace();
		}
		writeStepToTestNGReport("Capture risk address with commas: " + rtnAddress);
		return rtnAddress;
	}

	public String pSearchPsnapAndReturnRiskAddrWithCommas(String schBy, String policyNum, String addClaimOption, String lossDate) {
		// TODO Auto-generated method stub
		String rtnAddr;
		selectValueFromSearchBy(schBy);
		policyNum = getPolicyNumber(policyNum);
		enterPolicyNumber(policyNum);
		clickSearchButton();
		selectPolicyRecord(policyNum);
		rtnAddr = getRiskAddressWithCommas();
		selectPolicyAction(addClaimOption);
		lossDate = getLossDate(lossDate);
		enterLossDate(lossDate);
		clickGo();
		clickAddClaimIfDisplayed();
		return rtnAddr;
	}

	public void searchPolicy(String schBy, String policyNum) {
		// Auto-generated method stub
		selectValueFromSearchBy(schBy);
		policyNum = getPolicyNumber(policyNum);
		enterPolicyNumber(policyNum);
		clickSearchButton();
		selectPolicyRecord(policyNum);
	}

	public void pSnap(String addClaimOption, String lossDate) {
		// Auto-generated method stub
		selectPolicyAction(addClaimOption);
		lossDate = getLossDate(lossDate);
		enterLossDate(lossDate);
		clickGo();
		clickAddClaimIfDisplayed();
	}

}
