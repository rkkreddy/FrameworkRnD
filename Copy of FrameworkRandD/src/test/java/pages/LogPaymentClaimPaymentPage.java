package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class LogPaymentClaimPaymentPage extends BasePage {
	private By paymentMethodDdlLocator = By.id("CLM_SC_BO__PaymentMethod_30a");
	private By recapLetterDdlLocator = By.id("CLM_SC_BO_RecapLetter_30a");
	private By deliveryMethodDdlLocator = By.id("CLM_SC_BO__DeliveryMethod_30a");
	
	public void selectPaymentMethod(String paymentMethod) {
		writeStepToTestNGReport("Select payment method");
		new Select(driver.get().findElement(paymentMethodDdlLocator)).selectByVisibleText(paymentMethod);
	}

	public void selectRecapLetter(String recapLetter) {
		writeStepToTestNGReport("Select recap letter");
		new Select(driver.get().findElement(recapLetterDdlLocator)).selectByVisibleText(recapLetter);
	}

	public void selectDeliveryMethod(String deliveryMethod) {
		writeStepToTestNGReport("Select delivery method");
		new Select(driver.get().findElement(deliveryMethodDdlLocator )).selectByVisibleText(deliveryMethod);
	}
}
