package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class FinancialSummaryPage extends BasePage {
	
	private By financialSummaryTblLocator = By.id("igSummaryTabs_tabContent_1");
	
	public boolean isFinancialSummaryDisplayed()
    {
		writeStepToTestNGReport("Check if Financial Summary table is displayed");
        return driver.get().findElement(financialSummaryTblLocator).isDisplayed();
    }
	
}
