package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class PersonalInboxPage extends BasePage {

	private By signOutLocator = By.id("userControl_signOut");
	private By clearAllLocator = By.xpath("//div[@id='igLegend3']//button[.='Clear all']");
	private By restoreAllLocator = By.xpath("//div[@id='igLegend3']//button[.='Restore all']");
	private By applyButtonLocator = By.xpath("//div[@id='igLegend3']//button[.='Apply']");
	private By addClaimLinkLocator = By.linkText("Add claim");
	private By personalInboxTblLocator = By.id("tblInbox");
	private By quickSearchLocator = By.id("SC_UserControl_QuickSearch");
	private By quickSearchIconLocator = By.id("userControl_quickSearchIcon");

	public Boolean isSignoutDisplayed() {
		//return funx.IsObjDisplayed(driver, signOutLocator);
		//WebDriverWait wait = new WebDriverWait(driver.get(), 30);
   	 	//wait.until(ExpectedConditions.visibilityOfElementLocated(signOutLocator));
		//boolean isDisplayed = driver.get().findElement(signOutLocator).isDisplayed();//wowuc
		writeStepToTestNGReport("Check if SignOut link is displayed");
		boolean isDisplayed = waitUntilElementExists(signOutLocator, 6);
		//Reporter.log("@ Checking if the element %s is displayed and the result is %s.\n", signOutLocator.toString(), isDisplayed);
		return isDisplayed;

		//        return driver.findElement(signOutLocator).isDisplayed();//wowc
	}

	public void clickClearAllIfDisplayed() {
		writeStepToTestNGReport("Click Clear All button if displayed");
		if (driver.get().findElements(clearAllLocator).size() > 0) {
			driver.get().findElement(clearAllLocator).click();
			driver.get().findElement(applyButtonLocator).click();
		}

	}
	
	public void clickRestoreAllIfDisplayed() {
		writeStepToTestNGReport("Click Restore All button if displayed");
		if ((waitUntilElementExists(signOutLocator, 3)) && (driver.get().findElements(restoreAllLocator).size() > 0)) {
			WebDriverWait wait = new WebDriverWait(driver.get(), 30);
	    	wait.until(ExpectedConditions.elementToBeClickable(restoreAllLocator));
			driver.get().findElement(restoreAllLocator).click();
			driver.get().findElement(applyButtonLocator).click();
		}

	}

	public void clickAddClaim() {
		writeStepToTestNGReport("Click Add Claim link");
		driver.get().findElement(addClaimLinkLocator).click();
	}
	
	public boolean isInboxDisplayed() {
		writeStepToTestNGReport("Check if Inbox table is displayed");
        return driver.get().findElement(personalInboxTblLocator).isDisplayed();
    }
	
	public void enterClaimNumberInQuickSearch(String claimNumber) {
		writeStepToTestNGReport("Enter claim number in Quick Search field");
        driver.get().findElement(quickSearchLocator).sendKeys(claimNumber);
    }
	
	public void clickQuickSearchIcon() {
		writeStepToTestNGReport("Click Quick Search icon");
		driver.get().findElement(quickSearchIconLocator).click();
		progressSync();
	}

	public void searchClaimNumber(String claimNumString) {
		//Auto-generated method stub
		//Assert.assertTrue(isSignoutDisplayed());
		clickRestoreAllIfDisplayed();
		enterClaimNumberInQuickSearch(claimNumString);
		clickQuickSearchIcon();
	}

}
