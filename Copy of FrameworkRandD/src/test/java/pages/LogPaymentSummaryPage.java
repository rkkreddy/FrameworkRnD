package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class LogPaymentSummaryPage extends BasePage {
	private By flowFinishedBtnLocator = By.xpath("//button[@id='flowFinished']");
	
	public void clickFlowFinished()
    {
		writeStepToTestNGReport("Click Flow Finished(Checqured flag) button");
        driver.get().findElement(flowFinishedBtnLocator).click();
        progressSync();
        checkAndReportAppError();
    }
	
}
