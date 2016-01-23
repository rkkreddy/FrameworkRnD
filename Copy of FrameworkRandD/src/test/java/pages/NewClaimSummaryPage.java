package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class NewClaimSummaryPage extends BasePage {
	private By sendMailRbtnLocator = By.xpath("//div[@id='CLM_SC_035_SendMailChk_10a']/input[@value='<val>']");
    private String sendMailRbtnLocatorString = "//div[@id='CLM_SC_035_SendMailChk_10a']/input[@value='<val>']";
    private By flowFinishedBtnLocator = By.xpath("//button[@id='FNOL_FlowFinished']");
    private By claimSummaryTitleLocator = By.xpath("//div[@id='title']/h1");
    private By coverageTypeLocator = By.id("CLM_SC_048_CoverageType");
    private By assignmentDueDateLocator = By.id("CLM_SC_048_AssignmentDueDate");
    private By reserveCategoryLocator = By.id("CLM_SC_048_ReserveCategory");
    private By assignmentInstructionsLocator = By.id("CLM_SC_048_AssignmentInstructions");
    private By sidePanelControl = By.xpath("//a[@class='trigger active']");
    

    public void selectSendMail(String option) {
    	writeStepToTestNGReport("Select send mail");
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "no" : "yes";
        driver.get().findElement(By.xpath(sendMailRbtnLocatorString.replace("<val>", dmgRdoBtn))).click();
    }
    
    public String getClaimNum() {
    	writeStepToTestNGReport("Capture claim number");
    	//driver.get().findElement(sidePanelControl).click();
    	String claimTitleString = driver.get().findElement(claimSummaryTitleLocator).getText();
    	claimTitleString = claimTitleString.replaceAll("[^0-9]", "");
    	//claimTitleString = claimTitleString.replace("New claim summary > ", "");
    	writeStepToTestNGReport("Captured claim number: " + claimTitleString);
    	return claimTitleString;
		
	}
    
    public void selectCoverageType(String coverageType) {
    	writeStepToTestNGReport("Select coverage type");
    	new Select(driver.get().findElement(coverageTypeLocator)).selectByVisibleText(coverageType);
    	progressSync();
    }
    
    public void enterAssignmentDueDate(String adDate) {
    	writeStepToTestNGReport("Enter assignment due date");
    	driver.get().findElement(assignmentDueDateLocator).click();
		driver.get().findElement(assignmentDueDateLocator).sendKeys(adDate);
		
	}
    
    public void selectReserveCategory(String reserveCategory) {
    	writeStepToTestNGReport("Select reserve category");
    	new Select(driver.get().findElement(reserveCategoryLocator)).selectByVisibleText(reserveCategory);
    	progressSync();
    }
    
    public void enterAssignmentInstructions() {
    	writeStepToTestNGReport("Enter assignment instructions");
		driver.get().findElement(assignmentInstructionsLocator).sendKeys("SC5 claim assigned");
		
	}
	
	public void clickFlowFinished() {
		writeStepToTestNGReport("Click Flow Finished button");
        driver.get().findElement(flowFinishedBtnLocator).click();
        progressSync();
        checkAndReportAppError();
    }
	
	public void logClaimToResults(String claimNumString) {
		Reporter.log("<p><font face='verdana' color='green'>Claim created: " + claimNumString + "</font>");
	}

}
