package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class LogPaymentPayeePage extends BasePage {
	private String claimantLossRecordLocatorString = "//tr[contains(td[@id='Name'], '<payeeName>') and contains(td[@id='SubRolesForTable'], 'Notifier')]/td[@id='PrimaryPayee']/input[starts-with(@id, 'primary_')]";
	
	public void selectPayeeRadioBtn(String payeeName) {
		writeStepToTestNGReport("Select Payee radio button");
		driver.get().findElement(By.xpath(claimantLossRecordLocatorString.replace("<payeeName>", payeeName))).click();
	}
}
