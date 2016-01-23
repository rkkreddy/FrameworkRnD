package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;

public class ManageSIUReferralUpdateScorePage extends BasePage {
	private By SIUSummaryTextLocator = By.id("CLM_SC_BO_0008_SIUSummary_10a");
	private By fraudSummaryTableRowsLocator = By.xpath("//*[@id='CLM_SC_BO_0016_FraudSummary']/tbody/tr[@id]");
	private String fraudSummaryTableRowsLocatorString = "//*[@id='CLM_SC_BO_0016_FraudSummary']/tbody/tr[@id][<val>]";
	private By SIUSelectedTableRowsLocator = By.xpath("//*[@id='CLM_SC_BO_0151_SIUSelected']/tbody/tr[@id]");
	private String SIUSelectedTableRowsLocatorString = "//*[@id='CLM_SC_BO_0151_SIUSelected']/tbody/tr[@id][<val>]";
	private By overrideReasonDdlLocator = By.id("CLM_SC_BO_0016_RemovalReason");
	private By removeReasonBtnLocator = By.id("CLM_SC_BO_0151_RemoveButton");
	private By activeSIURowsLocator = By.xpath("//*[@id='CLM_SC_BO_0151_SIUSelected']/tbody/tr[contains(td[@id='ReasonStatus'], 'Active')]");
	private By activeSIUFirstRowLocator = By.xpath("//*[@id='CLM_SC_BO_0151_SIUSelected']/tbody/tr[contains(td[@id='ReasonStatus'], 'Active')][1]");
	
	public String getSIUSummaryText() {
		writeStepToTestNGReport("Capture SIU summary");
		// Auto-generated method stub
		String rtnSIUSummary = "";
		try {
			rtnSIUSummary = driver.get().findElement(SIUSummaryTextLocator).getText();
		} catch (NoSuchElementException nsee) {
			nsee.printStackTrace();
		}
		return rtnSIUSummary;
	}

	public int getFraudSummaryTableRowCount() {
		writeStepToTestNGReport("Get Fraud Summary table row count");
		// Auto-generated method stub
		return driver.get().findElements(fraudSummaryTableRowsLocator).size();
	}
	
	public int getSIUSelectedTableRowCount() {
		writeStepToTestNGReport("Get SIU Selected table row count");
		// Auto-generated method stub
		return driver.get().findElements(SIUSelectedTableRowsLocator).size();
	}

	public void closeAllSIUScores(String closeReason) {
		writeStepToTestNGReport("Close all SIU scores");
		// TODO Auto-generated method stub
		int fraudSummaryTblRowCount = getFraudSummaryTableRowCount();
		for (int i = 1; i <= fraudSummaryTblRowCount; i++) {
			driver.get().findElement(By.xpath(fraudSummaryTableRowsLocatorString.replace("<val>", Integer.toString(i)))).click();
			progressSync();
			//int SIUSelectedTblRowCount = getSIUSelectedTableRowCount();
			while (driver.get().findElements(activeSIURowsLocator).size() > 0) {
				driver.get().findElement(activeSIUFirstRowLocator).click();
				progressSync();
				new Select(driver.get().findElement(overrideReasonDdlLocator)).selectByVisibleText(closeReason);
				driver.get().findElement(removeReasonBtnLocator).click();
				progressSync();
			}
		}
	}
}
