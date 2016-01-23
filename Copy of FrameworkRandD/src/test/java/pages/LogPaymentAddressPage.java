package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class LogPaymentAddressPage extends BasePage {
	private By selectedPayeeRecordLocator = By.xpath("//*[@id='tblPayeeList']//tr[td/input[@checked='']]");
	private By firstAddressRecordLocator = By.xpath("//*[@id='CLM_SC_BO__AddressList']/tbody/tr[@realclass='even'][1]");
	private String claimantLossRecordLocatorString = "//tr[contains(td[@id='Name'], '<payeeName>') and contains(td[@id='SubRolesForTable'], 'Notifier')]/td[@id='PrimaryPayee']"; 

	public void selectSelectedPayeeRecord(String policyHolderName) {
		writeStepToTestNGReport("Select payee record");
		driver.get().findElement(By.xpath(claimantLossRecordLocatorString.replace("<payeeName>", policyHolderName))).click();
		progressSync();
	}
	
	public void selectPayeeFristAddressRecord() {
		writeStepToTestNGReport("Select payee address record");
		driver.get().findElement(firstAddressRecordLocator).click();
		progressSync();
	}
}
