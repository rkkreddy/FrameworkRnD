package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.javatuples.Pair;

public class NewClaimSetUpPage extends BasePage {
	 private By claimTypeDdlLocator = By.id("dhx_img_CLM_SC_2708_PerilCode_2c");
     private By associateEventNoBtnLocator = By.id("igUserInteractResponseNo");
     private By associateEventYesBtnLocator = By.id("igUserInteractResponseYes");
     private By riskAddressDdlLocator = By.id("CLM_SC_2708_RiskAddress_2d");
     private By policyHolderDdlLocator = By.id("CLM_SC_2708_PolicyHolder_2c");
     private String claimTypeLocatorString = "//div[@class='dhx_combo_list']/div[.='<claimType>']";
     Pair<String, String> pair;

     public void selectClaimType(String claimType) {
    	 writeStepToTestNGReport("Select calim type");
    	 WebDriverWait wait = new WebDriverWait(driver.get(), 30);
    	 wait.until(ExpectedConditions.elementToBeClickable(claimTypeDdlLocator));
         driver.get().findElement(claimTypeDdlLocator).click();
         //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(claimTypeLocatorString.replace("<claimType>", claimType))));
         //new Actions(driver.get()).moveToElement((WebElement) By.xpath(claimTypeLocatorString.replace("<claimType>", claimType))).perform();
         driver.get().findElement(By.xpath(claimTypeLocatorString.replace("<claimType>", claimType))).click();
         if (!driver.get().findElement(riskAddressDdlLocator).getAttribute("value").equalsIgnoreCase(claimType)) {
        	 driver.get().findElement(claimTypeDdlLocator).click();
        	 driver.get().findElement(By.xpath(claimTypeLocatorString.replace("<claimType>", claimType))).click();
         }
     }

     public void doNotAssociateEvent() {
    	 //Thread.currentThread().getStackTrace()[1].getMethodName();
    	 writeStepToTestNGReport("Do not associate event");
         if (driver.get().findElements(associateEventNoBtnLocator).size() > 0)
             driver.get().findElement(associateEventNoBtnLocator).click();
         progressSync();
         checkAndReportAppError();
     }

     public String getRiskAddress() {
    	 writeStepToTestNGReport("Capture risk address");
         return driver.get().findElement(riskAddressDdlLocator).getAttribute("value");
     }
     
     public String getPolicyHolderName() {
    	 writeStepToTestNGReport("Capture policy holder name");
         return driver.get().findElement(policyHolderDdlLocator).getText();
     }

	public String[] fillLossDetailsAndRtnRiskAddr(String lossType) {
		// TODO Auto-generated method stub
		String riskAddress, policyHolderName;
		selectClaimType(lossType);
		riskAddress = getRiskAddress();
		policyHolderName = getPolicyHolderName();
		clickFlowNext();
		doNotAssociateEvent();
		return new String[] {riskAddress, policyHolderName};
	}
}
