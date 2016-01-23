package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewClaimPartyPage extends BasePage {
	private By addClaimantLocator = By.id("btnAddClaimant");
	private By firstNameLocator = By.id("CLM_SC_099_FirstName");
	private By lastNameLocator = By.id("CLM_SC_099_LastName");
	private By searchLocator = By.xpath("//div[contains(@id, 'divSearchByType') and not(contains(@style, 'none'))]//*[@id='Search']");
	private By partyTypeLocator = By.id("CLM_SC_099_PartyTypeNew");
	private String partyTypeRadioBtnLocatorString = "//div[@id='divAddNewParty']//*[@id='CLM_SC_099_PartyType']";
	private By newSubRolesLocator = By.id("CLM_SC_099_NewSubRoles");
	private By addRoleBtnLocator = By.id("CLM_SC_099_Add");
	private By addPartyBtnLocator = By.id("btnSubmitNewClaimant");
	private By claimLabelLocator = By.xpath("//label[.='claim']");
	private String injuryClaimRadioBtnLocatorString = "//input[@id='CLM_SC_043_InjuryClaim_10a' and @value='<val>']";
	private String medicalAttnRadioBtnLocatorString = "//input[@id='CLM_SC_043_MedicalAttention_10a' and @value='<val>']";
	private String animalInjuryRadioBtnLocatorString = "//input[@id='CLM_SC_043_AnimalInjuryofDeath_10a' and @value='<val>']";
	private String propertyDmgRadioBtnLocatorString = "//input[@id='CLM_SC_043_RealProperty_10a' and @value='<val>']";
	

	public void clickAddClaimantButton() {
		writeStepToTestNGReport("Click Add Claimant button");
        driver.get().findElement(addClaimantLocator).click();
        progressSync();
    }
	
	public void enterFirstName(String firstName) {
		writeStepToTestNGReport("Enter first name");
        driver.get().findElement(firstNameLocator).sendKeys(firstName);
    }
	
	public void enterLastName(String lastName) {
		writeStepToTestNGReport("Enter last name");
        driver.get().findElement(lastNameLocator).sendKeys(lastName);
    }
	
	public void clickSearchButton() {
		writeStepToTestNGReport("Click Search Locator button");
        driver.get().findElement(searchLocator).click();
        progressSync();
    }
	
	public void selectPartyTypeNew(String partyType) {
		writeStepToTestNGReport("Select party type");
		WebDriverWait wait = new WebDriverWait(driver.get(), 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(partyTypeLocator));
        new Select(element).selectByVisibleText(partyType);
        progressSync();
    }
	
	public void selectPartyTypeRadioButton(String partyType) {
		writeStepToTestNGReport("Select Party Type radio button");
		WebDriverWait wait = new WebDriverWait(driver.get(), 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(partyTypeRadioBtnLocatorString + "/input[@value='I']")));
		if (partyType.trim().toUpperCase().equals("INDIVIDUAL")) {
			element.click();
		} else {
			driver.get().findElement(By.xpath(partyTypeRadioBtnLocatorString + "/input[@value='K']")).click();
		}
	}
	
	public void selectNewSubRole(String subRole) {
		writeStepToTestNGReport("Selct new sub role");
        new Select(driver.get().findElement(newSubRolesLocator)).selectByVisibleText(subRole);
        progressSync();
    }
	
	public void clickAddRoleButton() {
		writeStepToTestNGReport("Click Add Role button");
        driver.get().findElement(addRoleBtnLocator).click();
    }
	
	public void clickAddPartyButton() {
		writeStepToTestNGReport("Click Add Party button");
        driver.get().findElement(addPartyBtnLocator).click();
        progressSync();
    }
	
	public void clickClaimLabel() {
		writeStepToTestNGReport("Click Claim label");
        driver.get().findElement(claimLabelLocator).click();
    }
	
	public void selectInjuryClaimRadioBtn(String option) {
		writeStepToTestNGReport("Select Injury Claim radio button");
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(injuryClaimRadioBtnLocatorString.replace("<val>", dmgRdoBtn))).click();
    }
	
	public void selectMedicalAttnRadioBtn(String option) {
		writeStepToTestNGReport("Select Medical Attention radio button");
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(medicalAttnRadioBtnLocatorString.replace("<val>", dmgRdoBtn))).click();
    }
	
	public void selectAnimalInjuryRadioBtn(String option) {
		writeStepToTestNGReport("Select Animal Injury radion button");
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(animalInjuryRadioBtnLocatorString.replace("<val>", dmgRdoBtn))).click();
    }
	
	public void selectPropertyDamageRadioBtn(String option) {
		writeStepToTestNGReport("Select Property Damage radio button");
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(propertyDmgRadioBtnLocatorString.replace("<val>", dmgRdoBtn))).click();
    }
}
