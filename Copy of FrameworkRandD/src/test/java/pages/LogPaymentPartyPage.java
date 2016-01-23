package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class LogPaymentPartyPage extends BasePage {
	private By selectPartyDdlLocator = By.id("CLM_SC_BO_0108_Claimants_40a");
    private String selectPartyListLocatorString = "//div[@class='dhx_combo_list']/div[.='<party>'][1]";
	
	public void selectParty(String party) {
		writeStepToTestNGReport("Select party");
        driver.get().findElement(selectPartyDdlLocator).click();
        driver.get().findElement(By.xpath(selectPartyListLocatorString.replace("<party>", party))).click();
    }

}
