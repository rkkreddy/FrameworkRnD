package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import driverFactory.CustomWebDriver;

public class LogPaymentAnalysisPage extends BasePage {
    private String listLocatorString = "//div[@class='dhx_combo_list']/div[contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'), '<val>')][1]";
	private By riskDdlLocator = By.id("CLM_SC_BO_0111_Risk_50a");
	private By coverageFeatureDdlLocator = By.id("CLM_SC_BO_0111_CoverageCombined_50a");
	private String coverageFeaturelistLocatorString = "//div[@class='dhx_combo_list']/div[contains(.,'<val>')][1]";
	private By reserveDdlLocator = By.id("CLM_SC_BO_0111_ReserveType_50a");
	private String paymentCodelistLocatorString = "//*[@id='CLM_SC_BO_0111_PaymentCode_50a']/option[contains(.,'<val>')][1]";
	private By amountTxtBoxLocator = By.id("CLM_SC_BO_0111_Amount_50a");
	private By deductibleTxtBoxLocator = By.id("CLM_SC_BO_0111_DeductibleApplied_50a");
	private By paymentTypeDdlLocator = By.id("CLM_SC_BO_0111_PaymentType_50a");
	private By addBtnLocator = By.id("CLM_SC_BO_0111_LinePaymentAdd_50a");
	

	public void selectRiskAddress(String riskAddress) {
		writeStepToTestNGReport("Select risk address from Risk dropdown");
		driver.get().findElement(riskDdlLocator).click();
        driver.get().findElement(By.xpath(listLocatorString.replace("<val>", riskAddress.toUpperCase()))).click();
        progressSync();
	}

	public void selectCoverageFeature(String cvrgDdlVal) {
		writeStepToTestNGReport("Select coverage feature");
		driver.get().findElement(coverageFeatureDdlLocator).click();
        driver.get().findElement(By.xpath(coverageFeaturelistLocatorString.replace("<val>", cvrgDdlVal))).click();
        sleep(2);
        acceptAlert();
        progressSync();
        acceptAlert();
	}

	public void selectReserve(String reserveType) {
		writeStepToTestNGReport("Select reserve type");
		new Select(driver.get().findElement(reserveDdlLocator)).selectByVisibleText(reserveType);
		sleep(2);
		acceptAlert();
		sleep(2);
		progressSync();
		acceptAlert();
	}

	public void selectPaymentCode(String paymentCode) {
		writeStepToTestNGReport("Select payment code");
		driver.get().findElement(By.xpath(paymentCodelistLocatorString.replace("<val>", paymentCode))).click();
	}

	public void enterPayAmount(String payAmount) {
		writeStepToTestNGReport("Enter amount");
		driver.get().findElement(amountTxtBoxLocator).sendKeys(payAmount);
	}

	public void enterDeductible(String deductibleAmount) {
		writeStepToTestNGReport("Enter deductible");
		if (existsElement(deductibleTxtBoxLocator)) {
			if (driver.get().findElement(deductibleTxtBoxLocator).isEnabled()) {
				driver.get().findElement(deductibleTxtBoxLocator).sendKeys(deductibleAmount);
			}
		}
	}

	public void selectPaymentType(String paymentType) {
		writeStepToTestNGReport("Select payment type");
		new Select(driver.get().findElement(paymentTypeDdlLocator)).selectByVisibleText(paymentType);
	}

	public void clickAddBtn() {
		writeStepToTestNGReport("Click Add button");
		driver.get().findElement(addBtnLocator).click();
		progressSync();
	}

	public void fillPaymentAnalysisData(String riskAddressWithCommas,
			String cvrgDdlVal, String reserveType, String paymentCode,
			String payAmount, String deductibleAmount, String paymentType) {
		// Auto-generated method stub
		selectRiskAddress(riskAddressWithCommas);
		selectCoverageFeature(cvrgDdlVal);
		selectReserve(reserveType);
		selectPaymentCode(paymentCode);
		enterPayAmount(payAmount);
		enterDeductible(deductibleAmount);
		selectPaymentType(paymentType);
		clickAddBtn();
		clickFlowNext();
	}
}
