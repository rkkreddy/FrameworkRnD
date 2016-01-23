package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class IncidentPage extends BasePage {
	private By lossCauseDdlLocator = By.id("CLM_SC_2821_LossTypeCode_20a");
	private String lossCauseListLocatorString = "//div[@class='dhx_combo_list']/div[.='<lsTypCd>'][1]";
	private String weatherRelatedRadioBtnLocatorString = "//div[@id='CLM_SC_2821_WeatherRelatedIndicator_20a']/input[@value='<val>']";

    
    public void selectLossCause(String lossCause) {
    	writeStepToTestNGReport("Select loss cause");
        driver.get().findElement(lossCauseDdlLocator).click();
        driver.get().findElement(By.xpath(lossCauseListLocatorString.replace("<lsTypCd>", lossCause))).click();
    }
    
    public void selectWeatherRelatedRadioBtn(String option) {
    	writeStepToTestNGReport("Select Weather Related radio button");
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(weatherRelatedRadioBtnLocatorString.replace("<val>", dmgRdoBtn))).click();
    }

	public void fillLossData(String subLossType, String weatherRelated) {
		//Auto-generated method stub
		clickFlowEdit();
		selectLossCause(subLossType);
		selectWeatherRelatedRadioBtn(weatherRelated);
		clickFlowApply();
	}
}
