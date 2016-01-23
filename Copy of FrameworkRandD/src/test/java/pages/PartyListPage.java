package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PartyListPage extends BasePage {
	
	private String partyRecordLocatorString = "//tr[contains(td[@id='FullName'], '<partyName>') and contains(td[@id='SubRolesForTable'], 'Notifier')]"; 

	public void selectPartyRecord(String policyHolderName) {
		writeStepToTestNGReport("Select party record");
		driver.get().findElement(By.xpath(partyRecordLocatorString.replace("<partyName>", policyHolderName))).click();
		progressSync();
	}
	
}
