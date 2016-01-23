package Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.ITest;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import utilities.Generic.Mode;
import utilities.XLUtility;
import utilities.Generic;


public class FNOL extends BaseTest implements ITest{
	private static final long serialVersionUID = 1L;
	private String testName;
	private String userName;
	private String pwd;
	private String schBy;
	private String policyNum;
	private String lossDate;
	private String lossType;
	private String subLossType;
	private String interiorDmg, componentsDmg, exteriorDmg, detachedDmg, garageBarnDmg, otherDmg, personalPropDmg, foodPropDmg, otherPropDmg, estimateDmg, amountDmg;
	private String anthrPerDmg, anthrPerInjDmg, cntrEstDmg, dwellDmg;
	private String cvrgDdlVal, ttlLs, itmTypDdlVal, clmAmt, reserveType, thirdParty;
	//private 
	//private GetPropVals GetPropVal = new GetPropVals();
	
	
	@Factory (dataProvider = "dataFromXL") //(dataProviderClass = dataProvider.class, dataProvider = "dataFromXL")
	public FNOL(String testName, String userName, String pwd, String schBy, String policyNum, String lossDate, String lossType, String subLossType,
			String interiorDmg, String componentsDmg, String exteriorDmg, String detachedDmg, String garageBarnDmg, String otherDmg, 
			String personalPropDmg, String foodPropDmg, String otherPropDmg, String estimateDmg, String amountDmg, String dwellDmg, String anthrPerDmg, 
			String anthrPerInjDmg, String cntrEstDmg, String cvrgDdlVal, String totalLoss, String itmTypDdlVal, String clmAmt, String reserveType, String thirdParty)
	{
		this.testName = testName;
		this.userName = userName;
		this.pwd = pwd;
		this.schBy = schBy;
		this.policyNum = policyNum;
		this.lossDate = lossDate;
		this.lossType = lossType;
		this.subLossType = subLossType;
		this.interiorDmg = interiorDmg;
		this.componentsDmg = componentsDmg;
		this.exteriorDmg = exteriorDmg;
		this.detachedDmg = detachedDmg;
		this.garageBarnDmg = garageBarnDmg;
		this.otherDmg = otherDmg;
		this.personalPropDmg = personalPropDmg;
		this.foodPropDmg = foodPropDmg;
		this.otherPropDmg = otherPropDmg;
		this.estimateDmg = estimateDmg;
		this.amountDmg = amountDmg;
		this.dwellDmg = dwellDmg;
		this.anthrPerDmg = anthrPerDmg;
		this.anthrPerInjDmg = anthrPerInjDmg;
		this.cntrEstDmg = cntrEstDmg;
		this.cvrgDdlVal = cvrgDdlVal;
		this.ttlLs = totalLoss;
		this.itmTypDdlVal = itmTypDdlVal;
		this.clmAmt = clmAmt;
		this.reserveType = reserveType;
		this.thirdParty = thirdParty;

	}
	
	@BeforeMethod
	public void getTName()
	{
		getTestName();
	}
	@Override
	public String getTestName() {
		return testName + "( " + userName + " )";
	}

	@DataProvider(name = "dataFromXL", parallel = true)
	public static Object[][] xlData() throws Exception {

		return XLUtility.getTableArray("./data/TestData.xlsx", "Sheet1");

	}

	@Test(groups = { "functest", "fnol", "sanity" })
	public void FNOLTest() {
		
		//[start] loginPage
		//try{
		Reporter.log("<p>***** Log for - <b>" + testName + "</b> *****");
		//System.out.println(GetPropVal.getPropValue("SITUrl"));
		//Navigate to URL
		loginPage.openApplication();
		//start test
		//Enter user name
		loginPage.enterUserName(userName);
		
		//Enter password
		loginPage.enterPassword(pwd);

		//Click login button
		loginPage.clickLogin();
		
		//[end] loginPage
		
		//[start] personalInboxPage
		//Check if signout link is displayed
		Assert.assertTrue(personalInboxPage.isSignoutDisplayed());

		//Click on clear all if displayed
		personalInboxPage.clickClearAllIfDisplayed();

		//Click on Add Claim link
		personalInboxPage.clickAddClaim();
		
		//[end] personalInboxPage
		
		//[start] searchByPage
		//Check for search by drop down list
		Assert.assertTrue(searchByPage.isSearchByDisplayed());

		//Select Reference Number from Search By drop down list
		searchByPage.selectValueFromSearchBy(schBy);

		//Enter policy number
		searchByPage.enterPolicyNumber(policyNum);

		//Click on Search button
		searchByPage.clickSearchButton();
		searchByPage.progressSync();

		//Select policy record
		searchByPage.selectPolicyRecord(policyNum);
		searchByPage.progressSync();

		//Select policy action
		searchByPage.selectPolicyAction("Add claim");
		
		//Enter loss date
		String lsDate = searchByPage.getLossDate(lossDate);
		searchByPage.enterLossDate(lsDate);
		searchByPage.progressSync();

		//Click Go button
		searchByPage.clickGo();
		searchByPage.progressSync();

		//Click Add Claim if displayed
		searchByPage.clickAddClaimIfDisplayed();
		searchByPage.progressSync();
		
		//[end] searchByPage
		
		//[start] newClaimSetUpPage
		//Select loss type
		newClaimSetUpPage.selectClaimType(lossType);

		//Get risk address for future use
		String riskAddress = newClaimSetUpPage.getRiskAddress();

		//Click flow next
		newClaimSetUpPage.clickFlowNext();
		newClaimSetUpPage.progressSync();


		//Do not associate event if asked
		newClaimSetUpPage.doNotAssociateEvent();
		newClaimSetUpPage.progressSync();
		
		//[end] newClaimSetUpPage
		
		//[start] newClaimPolicyPage
		//Click next on Policy details
		newClaimPolicyPage.clickFlowNext();
		newClaimPolicyPage.progressSync();
		
		//[end] newClaimPolicyPage
		
		//[start] newClaimIncidentPage
		//Select address record
		newClaimIncidentPage.selectRiskAddressRecord(riskAddress);
		newClaimIncidentPage.progressSync();

		//Get question set size
		String quesData = XLUtility.getCellValBasedOnOtherCellVal("./data/ClaimTypes.xlsx", "Sheet1", "ClaimType", lossType, "QuesSet");
		if (quesData.trim().equals("S") || quesData.trim().equals("L")) {
			newClaimIncidentPage.selectAnotherPersonPropertyDamage(anthrPerDmg);
			newClaimIncidentPage.selectAnotherPersonInjuryDamage(anthrPerInjDmg);
			newClaimIncidentPage.selectContractorEstimateDamage(cntrEstDmg);
		}
		if (quesData.trim().equals("L")) {
			newClaimIncidentPage.selectInteriorDamage(interiorDmg);
			newClaimIncidentPage.selectCompenentsDamage(componentsDmg);
			newClaimIncidentPage.selectExteriorDamage(exteriorDmg);
			newClaimIncidentPage.selectDetachedDamage(detachedDmg);
			newClaimIncidentPage.selectGarageBarnDamage(detachedDmg, garageBarnDmg);
			newClaimIncidentPage.selectOtherDamage(otherDmg);
			newClaimIncidentPage.selectPersonalPropDamage(personalPropDmg);
			newClaimIncidentPage.selectFoodPropDamage(personalPropDmg, foodPropDmg);
			newClaimIncidentPage.selectOtherPropDamage(personalPropDmg, otherPropDmg);
			newClaimIncidentPage.selectEstimateDamage(estimateDmg);
			newClaimIncidentPage.selectEstimateAmountDamage(estimateDmg, amountDmg);
		}
		
		String splQuesData = XLUtility.getCellValBasedOnOtherCellVal("./data/ClaimTypes.xlsx", "Sheet1", "ClaimType", lossType, "SpecialQues");
		if (splQuesData.trim().equals("Y")) {
			newClaimIncidentPage.selectDwellingPropertyDamage(dwellDmg);
		}

		//Click Save button
		newClaimIncidentPage.clickSaveIncident();
		newClaimIncidentPage.progressSync();
		
		//Select address record
		newClaimIncidentPage.selectRiskAddressRecord(riskAddress);
		newClaimIncidentPage.progressSync();
		
		
		//Click flow next
		newClaimIncidentPage.clickFlowNext();
		newClaimIncidentPage.progressSync();
		
		//[end] newClaimIncidentPage
		
		//[start] newClaimPartyPage
		//************************add third party for liability claims*******************
		String tpClaimType = XLUtility.getCellValBasedOnOtherCellVal("./data/ClaimTypes.xlsx", "Sheet1", "ClaimType", lossType, "ThirdParty");
		String tpCoverage = XLUtility.getCellValBasedOnOtherCellVal("./data/Coverages.xlsx", "Coverages", "Coverage", cvrgDdlVal, "ThirdParty");
		Map<String, String> tpData = XLUtility.getDataSet("./data/ThirdParty.xlsx", "ThirdParty", thirdParty);
		
		if (tpClaimType.trim().equals("Y") || tpCoverage.trim().equals("Y")) {
			newClaimPartyPage.clickAddClaimantButton();
			newClaimPartyPage.progressSync();
			String firstName = Generic.generateRandomString(7, Mode.AlphaLC);
			newClaimPartyPage.enterFirstName(firstName);
			String lastName = Generic.generateRandomString(9, Mode.AlphaLC);
			newClaimPartyPage.enterLastName(lastName);
			newClaimPartyPage.clickSearchButton();
			newClaimPartyPage.selectPartyTypeNew(tpData.get("PartyTypeList"));
			newClaimPartyPage.selectPartyTypeRadioButton(tpData.get("PartyTypeRadioButton"));
			newClaimPartyPage.selectNewSubRole(tpData.get("SubRole"));
			newClaimPartyPage.clickAddRoleButton();
			newClaimPartyPage.clickAddPartyButton();
			newClaimPartyPage.clickClaimLabel();
			newClaimPartyPage.selectInjuryClaimRadioBtn(tpData.get("InjuryClaim"));
			newClaimPartyPage.selectMedicalAttnRadioBtn(tpData.get("MedicalAttn"));
			
			if (tpCoverage.trim().equals("N")) {
				newClaimPartyPage.selectAnimalInjuryRadioBtn(tpData.get("AnimalInjury"));
			}
			
			newClaimPartyPage.clickFlowApply();
			
			if (tpCoverage.trim().equals("N")) {
				newClaimPartyPage.selectNotifierRecord();
				newClaimPartyPage.clickClaimLabel();
				newClaimPartyPage.selectPropertyDamageRadioBtn(tpData.get("PropDmg"));
				newClaimPartyPage.clickFlowApply();
			}
		}
		//*******************************************************************************
		//Click flow next
		newClaimPartyPage.clickFlowNext();
		newClaimPartyPage.progressSync();
		
		//[end] newClaimPartyPage
		
		//[start] newClaimItemManagementPage
		//Select risk address record
		newClaimItemManagementPage.selectRiskAddressRecord(riskAddress);
		newClaimItemManagementPage.progressSync();

		//Select coverage
		newClaimItemManagementPage.selectCoverage(cvrgDdlVal);
		newClaimItemManagementPage.progressSync();

		//Click total loss if yes
		if (ttlLs.trim().toUpperCase().equals("YES"))
			newClaimItemManagementPage.clickTotalLoss();
		else
		{
			//Select item type
			newClaimItemManagementPage.selectItemType(itmTypDdlVal);

			//Enter claim amount
			newClaimItemManagementPage.enterClaimAmount(clmAmt);
		}

		//Click save item button
		newClaimItemManagementPage.clickSaveItem();
		newClaimItemManagementPage.progressSync();

		//Click flow next
		newClaimItemManagementPage.clickFlowNext();
		newClaimItemManagementPage.progressSync();
		
		//[end] newClaimItemManagementPage
		
		//[start] newClaimSummaryPage
		//Capture the severity code
		String claimNumString = newClaimSummaryPage.getClaimNum();
		String severityCodeString = newClaimIncidentPage.getSeverityCode(claimNumString);
		
		if (severityCodeString.equals("SC5")) {
			newClaimSummaryPage.selectCoverageType(cvrgDdlVal);
			newClaimSummaryPage.progressSync();
			
			newClaimSummaryPage.enterAssignmentDueDate(lsDate);
			
			newClaimSummaryPage.selectReserveCategory(reserveType);
			newClaimSummaryPage.progressSync();
			
			newClaimSummaryPage.enterAssignmentInstructions();
		}
		//Click flow finished
		newClaimSummaryPage.clickFlowFinished();
		newClaimSummaryPage.progressSync();
		
		//[end] newClaimSummaryPage

		//Check if personal inbox page is displayed
		Assert.assertTrue(personalInboxPage.isInboxDisplayed(), "FNOL process failed");

		//Click on logout
		loginPage.logout();
	}
	

}
