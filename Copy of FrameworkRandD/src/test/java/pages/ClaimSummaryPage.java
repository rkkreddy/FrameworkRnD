package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class ClaimSummaryPage extends BasePage {
	private By SIUSummaryTextLocator = By.id("CLM_SC_BO_0008_SIUSummary_10a");
	
	public String getSIUSummaryText() {
		writeStepToTestNGReport("Capture SIU Summary text");
		// TODO Auto-generated method stub
		String rtnSIUSummary = "";
		try {
			rtnSIUSummary = driver.get().findElement(SIUSummaryTextLocator).getText();
		} catch (NoSuchElementException nsee) {
			nsee.printStackTrace();
		}
		return rtnSIUSummary;
	}
    
}
