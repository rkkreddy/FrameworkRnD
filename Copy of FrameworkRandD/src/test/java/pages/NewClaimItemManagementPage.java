package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NewClaimItemManagementPage extends BasePage {
	
	private By coverageDdlLocator = By.id("CLM_SC_2847_CoverageCode_40b");
    private By totalLossChkbxLocator = By.id("CLM_SC_2847_TotalLoss_40b");
    private By itemTypeDdlLocator = By.id("CLM_SC_2851_ItemType_40b");
    private By claimAmtFldLocator = By.id("CLM_SC_2851_AmtClaimed_40b");
    private By saveItemBtnLocator = By.id("SaveItem_40b");

    public void selectCoverage(String coverage) {
    	writeStepToTestNGReport("Select coverage");
        new Select(driver.get().findElement(coverageDdlLocator)).selectByVisibleText(coverage);
        progressSync();
    }

    public void clickTotalLoss() {
    	writeStepToTestNGReport("Select Total Loss check box");
        driver.get().findElement(totalLossChkbxLocator).click();
    }

    public void selectItemType(String itemType) {
    	writeStepToTestNGReport("Select item type");
        driver.get().findElement(itemTypeDdlLocator).click();
        //WebElement element = driver.get().findElement(By.xpath("//div[@class='dhx_combo_list']/div[.='" + itemType + "'][1]"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        driver.get().findElement(By.xpath("//div[@class='dhx_combo_list']/div[.='" + itemType + "'][1]")).click();
        if (!driver.get().findElement(itemTypeDdlLocator).getAttribute("value").equalsIgnoreCase(itemType)) {
       	 driver.get().findElement(itemTypeDdlLocator).click();
       	try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
       	 driver.get().findElement(By.xpath("//div[@class='dhx_combo_list']/div[.='" + itemType + "'][1]")).click();
        }
    }

    public void enterClaimAmount(String claimAmount) {
    	writeStepToTestNGReport("Enter claim amount");
        driver.get().findElement(claimAmtFldLocator).clear();
        driver.get().findElement(claimAmtFldLocator).sendKeys(claimAmount);
    }

    public void clickSaveItem() {
    	writeStepToTestNGReport("Click Save Item button");
        driver.get().findElement(saveItemBtnLocator).click();
        progressSync();
    }

}
