package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;

public class SiuSpotlightPage extends BasePage {
	private By activeSIUReferralRowsLocator = By.xpath("//*[@id='tblReferrals']/tbody/tr[contains(td[@id='REFERRALSTATUSCODE'], 'Active')]");
	private By activeSIUReferralFirstRowLocator = By.xpath("//*[@id='tblReferrals']/tbody/tr[contains(td[@id='REFERRALSTATUSCODE'], 'Active')][1]");
	private By referralStatusDdlLocator = By.id("CLM_SC_BO_0152_ReferralStatus");
	private By reasonTxtBoxLocator = By.id("CLM_SC_BO_0152_Reason");
	
	
	public void closeAllActiveSIUReferrals(String refCloseReason, String reasonText) {
		writeStepToTestNGReport("Close all SIU referrals");
		while (driver.get().findElements(activeSIUReferralRowsLocator).size() > 0) {
			driver.get().findElement(activeSIUReferralFirstRowLocator).click();
			progressSync();
			new Select(driver.get().findElement(referralStatusDdlLocator)).selectByVisibleText(refCloseReason);
			driver.get().findElement(reasonTxtBoxLocator).sendKeys(reasonText);
			clickFlowApply();
			progressSync();
		}
	}
}
