package pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.testng.Reporter;

import utilities.DBUtility;
import utilities.XLUtility;

public class NewClaimIncidentPage extends BasePage {
//	private String anthrPerPropDmgRbtnLocatorString = "//div[@id='CLM_SC_39_ANOTHERPERSONPROPDMG_160a']/input[@value='<val>']";
//    private String anthrPerInjDmgRbtnLocatorString = "//div[@id='CLM_SC_39_ANOTHERPERSONINJURYDMG_160a']/input[@value='<val>']";
//    private String contrEstDmgRbtnLocatorString = "//div[@id='CLM_SC_39_CONTRACTORSESTIMATEDMG_160a']/input[@value='<val>']";
//    private String interiorDmgRbtnLocatorString = "//div[@id='CLM_SC_39_INTERIORDMG_150a']/input[@value='<val>']";
//    private String componentsDmgRbtnLocatorString = "//div[@id='CLM_SC_39_COMPONENTSDMG_150a']/input[@value='<val>']";
    private String dmgRbtnLocatorString = "//div[contains(@id,'CLM_SC_39_<damage>DMG')]/input[@value='<val>']";
	//private String dmgRbtnLocatorString = "//div[@id='CLM_SC_39_<damage>DMG_160a']/input[@value='<val>']";
    private String dmgAreaListLocatorString = "//select[@id='CLM_SC_39_<damageAreaUC>DMGLISTAVAIL_150a']/option[.='<damageOption>']";
    //private String dmgOptionsAddBtnLocatorString = "//div[@id='<damageAreaLC>Div']//input[@onclick='CLM_SC_39_<damageAreaUC>DMGLIST_150a.addAttribute()']";
    private String dmgOptionsAddBtnLocatorString = "//input[contains(@onclick, 'CLM_SC_39_<damageAreaUC>DMGLIST') and contains(@onclick, 'addAttribute')]";
    private By riskTblLocator = By.id("tblRisk");
    private By saveIncidentBtnLocator = By.id("CLM_SC_2820_SaveRiskDetails_140a");
    private By severityCodeBy = By.id("CLM_SC_39_SEVERITYCODE_160a");
    
    //private static DBUtility dbUtility = new DBUtility();
    public void selectDwellingPropertyDamage(String option) {
    	writeStepToTestNGReport("Select dwelling property damage - " + option);
    	String dmg = "DWELLINGDMG";
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
    }
    
    public void selectAnotherPersonPropertyDamage(String option) {
    	writeStepToTestNGReport("Select another person property damage - " + option);
    	String dmg = "ANOTHERPERSONPROP";
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
    }

    public void selectAnotherPersonInjuryDamage(String option) {
    	writeStepToTestNGReport("Select another person injury damage - " + option);
    	String dmg = "ANOTHERPERSONINJURY";
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
        //driver.get().findElement(By.xpath(anthrPerInjDmgRbtnLocatorString.replace("<val>", dmgRdoBtn))).click();
    }

    public void selectContractorEstimateDamage(String option) {
    	writeStepToTestNGReport("Select contractor estimate damage - " + option);
    	String dmg = "CONTRACTORSESTIMATE";
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
        //driver.get().findElement(By.xpath(contrEstDmgRbtnLocatorString.replace("<val>", dmgRdoBtn))).click();
    }
    
    public void selectInteriorDamage(String option) {
    	writeStepToTestNGReport("Select interior damage - " + option);
    	String dmg = "INTERIOR";
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
        //driver.get().findElement(By.xpath(interiorDmgRbtnLocatorString.replace("<val>", dmgRdoBtn))).click();
        if (dmgRdoBtn.equals("1")) {
        	this.selectDamageOptions(dmg, option);
        }
    }
    
    public void selectCompenentsDamage(String option) {
    	writeStepToTestNGReport("Select compenents damage - " + option);
    	String dmg = "COMPONENTS";
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
        //driver.get().findElement(By.xpath(componentsDmgRbtnLocatorString.replace("<val>", dmgRdoBtn))).click();
        if (dmgRdoBtn.equals("1")) {
        	this.selectDamageOptions(dmg, option);
        }
    }
    
    public void selectExteriorDamage(String option) {
    	writeStepToTestNGReport("Select exterior damage - " + option);
    	String dmg = "EXTERIOR";
        String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
        if (dmgRdoBtn.equals("1")) {
        	this.selectDamageOptions(dmg, option);
        }
    }
    
    public void selectDamageOptions(String dmgArea, String dmgOptions) {
    	String[] dmgOpts = dmgOptions.split("\\|");
    	dmgArea = dmgArea.trim();
    	//String dmgAreaLC = dmgArea.toLowerCase();
    	String dmgAreaUC = dmgArea.toUpperCase();
    	for (int i = 0; i < dmgOpts.length; i++)
        {
    		driver.get().findElement(By.xpath(dmgAreaListLocatorString.replace("<damageAreaUC>", dmgAreaUC).replace("<damageOption>", dmgOpts[i]))).click();
    		//driver.get().findElement(By.xpath("//select[@id='CLM_SC_39_" + dmgArea.toUpperCase() + "DMGLISTAVAIL_150a']/option[.='" + dmgOpts[i] + "']")).click();
    		//driver.get().findElement(By.xpath(dmgOptionsAddBtnLocatorString.replace("<damageAreaLC>", dmgAreaLC).replace("<damageAreaUC>", dmgAreaUC))).click();
    		driver.get().findElement(By.xpath(dmgOptionsAddBtnLocatorString.replace("<damageAreaUC>", dmgAreaUC))).click();
    		//driver.get().findElement(By.xpath("//div[@id='" + dmgArea.toLowerCase() + "Div']//input[@onclick='CLM_SC_39_" + dmgArea.toUpperCase() + "DMGLIST_150a.addAttribute()']")).click();
        }
	}
    
    public void clickSaveIncident() {
    	writeStepToTestNGReport("Click Save Incident button");
        driver.get().findElement(saveIncidentBtnLocator).click();
        progressSync();
    }

	public void selectDetachedDamage(String option) {
		writeStepToTestNGReport("Select detatched damage - " + option);
		String dmgRdoBtn = (option.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", "DETACHED").replace("<val>", dmgRdoBtn))).click();
	}

	public void selectGarageBarnDamage(String detachedDmg, String garageBarnDmg) {
		writeStepToTestNGReport("Select garage barn damage - " + garageBarnDmg);
		String dmg = "GARAGEBARN";
		String dmgRdoBtn = (detachedDmg.trim().toUpperCase().equals("NO")) ? "0" : "1";
		if (dmgRdoBtn.equals("1")) {
			dmgRdoBtn = (garageBarnDmg.trim().toUpperCase().equals("NO")) ? "0" : "1";
			driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
        	if (dmgRdoBtn.equals("1")) {
        		dmg = "DETACHED";
        		this.selectDamageOptions(dmg, garageBarnDmg);
        	}
        }
		
	}

	public void selectOtherDamage(String otherDmg) {
		writeStepToTestNGReport("Select other damage - " + otherDmg);
		String dmg = "OTHER";
        String dmgRdoBtn = (otherDmg.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
        if (dmgRdoBtn.equals("1")) {
        	this.selectDamageOptions(dmg, otherDmg);
        }
	}

	public void selectPersonalPropDamage(String personalPropDmg) {
		writeStepToTestNGReport("Select personal property damage - " + personalPropDmg);
		String dmg = "PERSONALPROP";
        String dmgRdoBtn = (personalPropDmg.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
	}

	public void selectFoodPropDamage(String personalPropDmg, String foodPropDmg) {
		writeStepToTestNGReport("Select food property damage - " + foodPropDmg);
		String dmg = "FOODPROP";
		String dmgRdoBtn = (personalPropDmg.trim().toUpperCase().equals("NO")) ? "0" : "1";
		if (dmgRdoBtn.equals("1")) {
			dmgRdoBtn = (foodPropDmg.trim().toUpperCase().equals("NO")) ? "0" : "1";
	        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
		}
		
	}

	public void selectOtherPropDamage(String personalPropDmg, String otherPropDmg) {
		writeStepToTestNGReport("Select other property damage - " + otherPropDmg);
		String dmg = "FOODPROP";
		String dmgRdoBtn = (personalPropDmg.trim().toUpperCase().equals("NO")) ? "0" : "1";
		if (dmgRdoBtn.equals("1")) {
			dmgRdoBtn = (otherPropDmg.trim().toUpperCase().equals("NO")) ? "0" : "1";
	        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
		}
	}

	public void selectEstimateDamage(String estimateDmg) {
		writeStepToTestNGReport("Select estimate damage - " + estimateDmg);
		String dmg = "ESTIMATE";
        String dmgRdoBtn = (estimateDmg.trim().toUpperCase().equals("NO")) ? "0" : "1";
        driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", dmg).replace("<val>", dmgRdoBtn))).click();
	}

	public void selectEstimateAmountDamage(String estimateDmg, String amountDmg) {
		writeStepToTestNGReport("Select estimate amount damage - " + estimateDmg);
		String overDmg = "OVER";
		String underDmg = "UNDER";
		amountDmg = amountDmg.trim().toUpperCase();
		String dmgRdoBtn = (estimateDmg.trim().toUpperCase().equals("NO")) ? "0" : "1";
		if (dmgRdoBtn.equals("1")) {
			if (amountDmg.equals("OVER")) {
				driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", overDmg).replace("<val>", "1"))).click();
			} else {
				driver.get().findElement(By.xpath(dmgRbtnLocatorString.replace("<damage>", underDmg).replace("<val>", "1"))).click();
			}
		}
		
	}
	
	public String getSeverityCode(String claimNumString) {
		writeStepToTestNGReport("Capture severity code");
		String rtnSvrCdString = null;
		String severityCodeQryString = XLUtility.getCellValBasedOnOtherCellVal("./data/SQLQueries.xlsx", "Queries", "Name", "SeverityCode", "Query");
		String qryString = severityCodeQryString.replace("<ClaimNumber>", claimNumString.trim());
		try {
			String dbRtnVal = (String) DBUtility.getDBVal("CMS", qryString, "efsxml");
			//<EFS><EFS_RECORDONLY>0</EFS_RECORDONLY><EFS_DISPATCHSEVERITY>SC1</EFS_DISPATCHSEVERITY><EFS_DISPATCHSEVERITYOTHER>SC1</EFS_DISPATCHSEVERITYOTHER></EFS>
			Pattern pattern = Pattern.compile("<EFS_DISPATCHSEVERITY>(.*?)</EFS_DISPATCHSEVERITY>");
			Matcher matcher = pattern.matcher(dbRtnVal);
			if (matcher.find())
			{
				rtnSvrCdString = matcher.group(1);
			}
		} catch (NullPointerException npe){
			Reporter.log("<p><font face='verdana' color='red'>Error: No data returned from the query: '" + qryString + "'.</font>");
		}
		return rtnSvrCdString;
		
	}

	public void answerSmallQuestionSet(String anthrPerDmg, String anthrPerInjDmg, String cntrEstDmg) {
		//Auto-generated method stub
		this.selectAnotherPersonPropertyDamage(anthrPerDmg);
		this.selectAnotherPersonInjuryDamage(anthrPerInjDmg);
		this.selectContractorEstimateDamage(cntrEstDmg);
	}

	public void answerLargeQuestionSet(String interiorDmg,
			String componentsDmg, String exteriorDmg, String detachedDmg,
			String garageBarnDmg, String otherDmg, String personalPropDmg,
			String foodPropDmg, String otherPropDmg, String estimateDmg,
			String amountDmg) {
		//Auto-generated method stub
		this.selectInteriorDamage(interiorDmg);
		this.selectCompenentsDamage(componentsDmg);
		this.selectExteriorDamage(exteriorDmg);
		this.selectDetachedDamage(detachedDmg);
		this.selectGarageBarnDamage(detachedDmg, garageBarnDmg);
		this.selectOtherDamage(otherDmg);
		this.selectPersonalPropDamage(personalPropDmg);
		this.selectFoodPropDamage(personalPropDmg, foodPropDmg);
		this.selectOtherPropDamage(personalPropDmg, otherPropDmg);
		this.selectEstimateDamage(estimateDmg);
		this.selectEstimateAmountDamage(estimateDmg, amountDmg);
		
	}
	
	
	
	

}
