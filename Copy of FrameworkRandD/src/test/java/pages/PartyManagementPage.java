package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PartyManagementPage extends BasePage {
	private By addNewBankACBtnLocator = By.id("AccountAddNew");
	private By accountTypeLocator = By.xpath("//select[@id='AccountType']");
	private By defaultACChkBoxLocator = By.id("CLM_SC_013_DefaultAccount_10a");
	private By bankACNumberTxtBoxLocator = By.id("CLM_SC_013_BankAccNo_10a");
	private By routingNumberTxtBoxLocator = By.id("CLM_SC_013_SortCode_10a");
	private By validateBtnLocator = By.id("accountValidate_10a");
	private By saveBankACBtnLocator = By.id("accountSave_10a");

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
		writeStepToTestNGReport("Enter bank AC number");
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
		writeStepToTestNGReport("Click Save Bank Ac button");
		driver.get().findElement(saveBankACBtnLocator).click();
		progressSync();
	}

	public void addBankAC(String bankACType, String defaultAC,
			String bankACNumber, String routingNumber) {
		//Auto-generated method stub
		clickFlowEdit();
		clickAddNewBankACButton();
		selectBankACType(bankACType);
		selectDefaultAC(defaultAC);
		enterBankACNumber(bankACNumber);
		enterRoutingNumber(routingNumber);
		clickValidateBtn();
		clickSaveBankACBtn();
		clickFlowApply();
	}

}
