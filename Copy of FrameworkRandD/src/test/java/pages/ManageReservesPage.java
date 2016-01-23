package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class ManageReservesPage extends BasePage {
	private By addNewBankACBtnLocator = By.id("AccountAddNew");
	private By accountTypeLocator = By.xpath("//select[@id='AccountType']");
	private By defaultACChkBoxLocator = By.id("CLM_SC_013_DefaultAccount_10a");
	private By bankACNumberTxtBoxLocator = By.id("CLM_SC_013_BankAccNo_10a");
	private By routingNumberTxtBoxLocator = By.id("CLM_SC_013_SortCode_10a");
	private By validateBtnLocator = By.id("accountValidate_10a");
	private By saveBankACBtnLocator = By.id("accountSave_10a");
	private String claimantLossRecordLocatorString = "//tr[(td[@id='CLAIMANT'] = '<claimant>') and (td[@id='RESERVETYPE'] = '<reserveType>')][1]";
	private By reserveAmountTxtBoxLocator = By.id("CLM_SC_BO_0101_NewOS_50a");
	private By reserveCancelBtnLocator = By.id("CLM_SC_BO_0101_Cancel");
	private By reserveUpdateBtnLocator = By.id("CLM_SC_BO_0101_Update");
	private By reserveConfirmBtnLocator = By.id("CLM_SC_BO_0101_Confirm");

	public void clickAddNewBankACButton() {
		writeStepToTestNGReport("Click Add New Bank AC button");
		driver.get().findElement(addNewBankACBtnLocator).click();
	}

	public void selectBankACType(String accountType) {
		writeStepToTestNGReport("Select bank AC type");
		new Select(driver.get().findElement(accountTypeLocator)).selectByVisibleText(accountType);
	}

	public void selectDefaultAC(String defaultAC) {
		writeStepToTestNGReport("Select Default AC check box");
		defaultAC = defaultAC.trim().toUpperCase();
		boolean isSelected = driver.get().findElement(defaultACChkBoxLocator).isSelected();
		if (defaultAC.equals("YES")) {
			if (!isSelected)
				driver.get().findElement(defaultACChkBoxLocator).click();
		} else {
			if (isSelected)
				driver.get().findElement(defaultACChkBoxLocator).click();
		}
	}

	public void enterBankACNumber(String bankACNumber) {
		writeStepToTestNGReport("Enter bank account number");
		driver.get().findElement(bankACNumberTxtBoxLocator).sendKeys(bankACNumber);
	}

	public void enterRoutingNumber(String routingNumber) {
		writeStepToTestNGReport("Enter routing number");
		driver.get().findElement(routingNumberTxtBoxLocator).click();
		driver.get().findElement(routingNumberTxtBoxLocator).sendKeys(routingNumber);
	}

	public void clickValidateBtn() {
		writeStepToTestNGReport("Click Validate button");
		driver.get().findElement(validateBtnLocator).click();
		progressSync();
	}

	public void clickSaveBankACBtn() {
		writeStepToTestNGReport("Click Save Bank AC button");
		driver.get().findElement(saveBankACBtnLocator).click();
		progressSync();
	}

	public void selectClaimantLossRecord(String policyHolderName, String reserveType) {
		writeStepToTestNGReport("Select claimant loss record");
		driver.get().findElement(By.xpath((claimantLossRecordLocatorString.replace("<claimant>", policyHolderName)).replace("<reserveType>", reserveType))).click();
	}

	public String enterAndGetReserveAmount(String reserveAmount) {
		String rtnReserveAmt = reserveAmount;
		String reserveAmtFromApp = driver.get().findElement(reserveAmountTxtBoxLocator).getAttribute("value");
		Float reserveAmtFromAppFloat = Float.parseFloat(reserveAmtFromApp);
		Float reserveAmtForInput = Float.parseFloat(reserveAmount);
		if (reserveAmtFromAppFloat >= reserveAmtForInput){
			rtnReserveAmt = Float.toString(reserveAmtFromAppFloat);
			driver.get().findElement(reserveConfirmBtnLocator).click();
		} else {
			driver.get().findElement(reserveAmountTxtBoxLocator).sendKeys(reserveAmount);
			driver.get().findElement(reserveUpdateBtnLocator).click();
			progressSync();
		}
		return rtnReserveAmt;
	}

}
