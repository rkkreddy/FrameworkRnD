package Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.ITest;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import utilities.Generic.Mode;
import utilities.GetPropVals;
import utilities.XLUtility;
import utilities.Generic;
import org.slf4j.*;


public class FNOLDataDriven extends BaseTest implements ITest{
	static Logger LOGGER = LoggerFactory.getLogger(FNOLDataDriven.class);
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
	private String cvrgDdlVal, ttlLs, itmTypDdlVal, clmAmt, reserveType, createReserveFor;
	private String partyTypeList, partyTypeRadioButton, subRole, injuryClaim, medicalAttn, animalInjury, propDmg;
	String lsDate, riskAddress, policyHolderName, quesData, splQuesData, tpClaimType, tpCoverage, tpFirstName, tpLastName, claimNumString, severityCodeString;
	String weatherRelated, bankACType, defaultAC, bankACNumber, routingNumber, reserveAmount, enteredReserveAmount, paymentCode, payAmount, deductibleAmount, paymentType;
	String paymentMethod, recapLetter, riskAddressWithCommas, paymentKey, deliveryMethod;
	Map<String, String> usrData, clmTypeData, dmgData, lsData, tpData, paymentData, SIUData;
	String SIUKey, SIUStatusMsg, SIUCloseReason, referralCloseReason, reasonText;
	private GetPropVals getPropVal = new GetPropVals();
	private String userKey;


	@Factory (dataProvider = "dataFromXL")
	public FNOLDataDriven(String testName, String userData, String schBy, String policyNum, String lossDate, String claimTypeKey, 
			String damageKey, String lossKey, String thirdPartyKey, String paymentKey, String SIUKey) {
		//String env = System.getProperty("Environment");
		this.userKey = userData;
		clmTypeData = XLUtility.getDataSet("./data/ClaimType&SubType.xlsx", "ClaimTypeSubType", claimTypeKey);
		dmgData = XLUtility.getDataSet("./data/DamageData.xlsx", "DamageData", damageKey);
		lsData = XLUtility.getDataSet("./data/LossData.xlsx", "LossData", lossKey);
		tpData = XLUtility.getDataSet("./data/ThirdParty.xlsx", "ThirdParty", thirdPartyKey);
		paymentData = XLUtility.getDataSet("./data/PaymentData.xlsx", "Payments", paymentKey);
		SIUData = XLUtility.getDataSet("./data/SIUData.xlsx", "SIUData", SIUKey);
		this.testName = testName;
		
		this.schBy = schBy;
		this.policyNum = policyNum;
		this.lossDate = lossDate;
		this.lossType = clmTypeData.get("ClaimType");
		this.subLossType = clmTypeData.get("SubType");
		this.interiorDmg = dmgData.get("InteriorDmg");
		this.componentsDmg = dmgData.get("ComponentDmg");
		this.exteriorDmg = dmgData.get("ExteriorDmg");
		this.detachedDmg = dmgData.get("DetachedDmg");
		this.garageBarnDmg = dmgData.get("GarageBarnDmg");
		this.otherDmg = dmgData.get("OtherStructureDmg");
		this.personalPropDmg = dmgData.get("PersonalPropDmg");
		this.foodPropDmg = dmgData.get("PersonalFoodDmg");
		this.otherPropDmg = dmgData.get("PersonalOtherDmg");
		this.estimateDmg = dmgData.get("EstimateDmg");
		this.amountDmg = dmgData.get("Over/Under5K");
		this.dwellDmg = dmgData.get("DwelDmg");
		this.anthrPerDmg = dmgData.get("AnthrPerDmg");
		this.anthrPerInjDmg = dmgData.get("AnthrPerInjDmg");
		this.cntrEstDmg = dmgData.get("CntrEstDmg");
		this.cvrgDdlVal = lsData.get("CvrgDdlVal");
		this.ttlLs = lsData.get("TtlLoss");
		this.itmTypDdlVal = lsData.get("ItmTypDdlVal");
		this.clmAmt = lsData.get("ClmAmt");
		this.reserveType = lsData.get("ReserveType");
		this.partyTypeList = tpData.get("PartyTypeList");
		this.partyTypeRadioButton = tpData.get("PartyTypeRadioButton");
		this.subRole = tpData.get("SubRole");
		this.injuryClaim = tpData.get("InjuryClaim");
		this.medicalAttn = tpData.get("MedicalAttn");
		this.animalInjury = tpData.get("AnimalInjury");
		this.propDmg = tpData.get("PropDmg");
		this.weatherRelated = paymentData.get("WeatherRelated");
		this.bankACType = paymentData.get("BankACType");
		this.defaultAC = paymentData.get("DefaultAC");
		this.routingNumber = paymentData.get("RtgNumber");
		this.reserveAmount = paymentData.get("ReserveAmount");
		this.paymentCode = paymentData.get("PaymentCode");
		this.payAmount = paymentData.get("PayAmount");
		this.deductibleAmount = paymentData.get("Deductible");
		this.paymentType = paymentData.get("PaymentType");
		this.deliveryMethod = paymentData.get("DeliveryMethod");
		this.paymentMethod = paymentData.get("PaymentMethod");
		this.recapLetter = paymentData.get("RecapLetter");
		this.paymentKey = paymentKey;
		this.SIUStatusMsg = SIUData.get("SIUStatusMsg");
		this.SIUCloseReason = SIUData.get("SIUCloseReason");
		this.referralCloseReason = SIUData.get("RefCloseReason");
		this.reasonText = SIUData.get("ReasonText");
	}

	@BeforeMethod(alwaysRun = true)
	public void getTName()
	{
		getTestName();
		
	}
	@BeforeMethod(alwaysRun = true)
	public void BeforeTest() {
		String env = System.getProperty("Environment");
		if (env == null) { env = getPropVal.getPropValue("Env"); }
		Reporter.log("<p> ==> Getting user data for " + env);
		usrData = XLUtility.getDataSet("./data/UserCredentialData.xlsx", env, userKey);
		this.userName = usrData.get("UserName");
		this.pwd = usrData.get("Password");
	}
	@Override
	public String getTestName() {
		return testName + "( " + userName + " )";
	}

	@DataProvider(name = "dataFromXL", parallel = true)
	public static Object[][] xlData() throws Exception {

		return XLUtility.getTableArray("./data/FNOLTestData.xlsx", "Sheet1");

	}

	@Test(groups = { "functest", "fnol", "sanity" })
	public void FNOLTest() {
		LOGGER.info("<p>***** Log for - <font face='verdana' color='blue'><b> FNOL - " + testName + "</b></font> *****");
		Reporter.log("<p>***** Log for - <font face='verdana' color='blue'><b> FNOL - " + testName + "</b></font> *****");
		
		loginPage.login(userName, pwd);

		Assert.assertTrue(personalInboxPage.isSignoutDisplayed());
		personalInboxPage.clickRestoreAllIfDisplayed();
		personalInboxPage.clickAddClaim();

		Assert.assertTrue(searchByPage.isSearchByDisplayed());
		riskAddressWithCommas = searchByPage.pSearchPsnapAndReturnRiskAddrWithCommas(schBy, policyNum, "Add claim", lossDate);
		lsDate = searchByPage.getLossDate(lossDate);

		String[] riskAddrAndPolicyHolderName = newClaimSetUpPage.fillLossDetailsAndRtnRiskAddr(lossType);
		riskAddress = riskAddrAndPolicyHolderName[0];
		policyHolderName = riskAddrAndPolicyHolderName[1];

		newClaimPolicyPage.clickFlowNext();

		newClaimIncidentPage.selectRiskAddressRecord(riskAddress);
		quesData = XLUtility.getCellValBasedOnOtherCellVal("./data/ClaimTypes.xlsx", "Sheet1", "ClaimType", lossType, "QuesSet");
		if (quesData.trim().equals("S") || quesData.trim().equals("L")) {
			newClaimIncidentPage.answerSmallQuestionSet(anthrPerDmg, anthrPerInjDmg, cntrEstDmg);
		}
		if (quesData.trim().equals("L")) {
			newClaimIncidentPage.answerLargeQuestionSet(interiorDmg, componentsDmg, exteriorDmg, detachedDmg, garageBarnDmg, otherDmg, personalPropDmg, 
					foodPropDmg, otherPropDmg, estimateDmg, amountDmg);
		}

		splQuesData = XLUtility.getCellValBasedOnOtherCellVal("./data/ClaimTypes.xlsx", "Sheet1", "ClaimType", lossType, "SpecialQues");
		if (splQuesData.trim().equals("Y")) {
			newClaimIncidentPage.selectDwellingPropertyDamage(dwellDmg);
		}
		newClaimIncidentPage.clickSaveIncident();
		newClaimIncidentPage.clickFlowNext();

		tpClaimType = XLUtility.getCellValBasedOnOtherCellVal("./data/ClaimTypes.xlsx", "Sheet1", "ClaimType", lossType, "ThirdParty");
		tpCoverage = XLUtility.getCellValBasedOnOtherCellVal("./data/Coverages.xlsx", "Coverages", "Coverage", cvrgDdlVal, "ThirdParty");
		if (tpClaimType.trim().equals("Y") || tpCoverage.trim().equals("Y")) {
			newClaimPartyPage.clickAddClaimantButton();
			tpFirstName = Generic.generateRandomString(7, Mode.AlphaLC);
			newClaimPartyPage.enterFirstName(tpFirstName);
			tpLastName = Generic.generateRandomString(9, Mode.AlphaLC);
			newClaimPartyPage.enterLastName(tpLastName);
			newClaimPartyPage.clickSearchButton();
			newClaimPartyPage.selectPartyTypeNew(partyTypeList);
			newClaimPartyPage.selectPartyTypeRadioButton(partyTypeRadioButton);
			newClaimPartyPage.selectNewSubRole(subRole);
			newClaimPartyPage.clickAddRoleButton();
			newClaimPartyPage.clickAddPartyButton();
			newClaimPartyPage.clickClaimLabel();
			newClaimPartyPage.selectInjuryClaimRadioBtn(injuryClaim);
			newClaimPartyPage.selectMedicalAttnRadioBtn(medicalAttn);
			if (tpCoverage.trim().equals("N"))
				newClaimPartyPage.selectAnimalInjuryRadioBtn(animalInjury);
			newClaimPartyPage.clickFlowApply();
			if (tpCoverage.trim().equals("N")) {
				newClaimPartyPage.selectNotifierRecord();
				newClaimPartyPage.clickClaimLabel();
				newClaimPartyPage.selectPropertyDamageRadioBtn(propDmg);
				newClaimPartyPage.clickFlowApply();
			}
			newClaimPartyPage.clickFlowNext();

			newClaimItemManagementPage.selectRiskAddressRecord(riskAddress);
			newClaimItemManagementPage.selectCoverage(cvrgDdlVal);
			if (ttlLs.trim().toUpperCase().equals("YES"))
				newClaimItemManagementPage.clickTotalLoss();
			else
			{
				newClaimItemManagementPage.selectItemType(itmTypDdlVal);
				newClaimItemManagementPage.enterClaimAmount(clmAmt);
			}
			newClaimItemManagementPage.clickSaveItem();
			newClaimItemManagementPage.clickFlowNext();

			claimNumString = newClaimSummaryPage.getClaimNum();
			severityCodeString = newClaimIncidentPage.getSeverityCode(claimNumString);
			if (severityCodeString.equals("SC5")) {
				newClaimSummaryPage.selectCoverageType(cvrgDdlVal);
				newClaimSummaryPage.enterAssignmentDueDate(lsDate);
				newClaimSummaryPage.selectReserveCategory(reserveType);
				newClaimSummaryPage.enterAssignmentInstructions();
			}
			newClaimSummaryPage.clickFlowFinished();

			Assert.assertTrue(personalInboxPage.isInboxDisplayed(), "FNOL process failed");
			newClaimSummaryPage.logClaimToResults(claimNumString);
			loginPage.logout();
		}
	}

	@Test(groups = { "functest", "payments", "sanity" }, dependsOnMethods = "FNOLTest")
	public void PaymentTest() {
		//Reporter.log("<p>***** Log for - <b>" + "Payment Test" + "</b> *****");
		//Reporter.log("<p>***** Log for - <font face='verdana' color='blue'><b>" + "Payment Test" + "</b></font> *****");
		Reporter.log("<p>***** Log for - <font face='verdana' color='blue'><b> Payment - " + testName + "</b></font> *****");
		loginPage.login(userName, pwd);

		personalInboxPage.searchClaimNumber(claimNumString);

		String SIUSummaryText = claimSummaryPage.getSIUSummaryText();
		if (SIUSummaryText.trim().equalsIgnoreCase(SIUStatusMsg)) {
			claimSummaryPage.clickManageSIUReferralUpdateScoreLink();
			manageSIUReferralUpdateScorePage.clickFlowEdit();
			manageSIUReferralUpdateScorePage.closeAllSIUScores(SIUCloseReason);
			claimSummaryPage.clickSpotlightLink();
			siuSpotlightPage.closeAllActiveSIUReferrals(referralCloseReason, reasonText);
			siuSpotlightPage.clickClaimSummary();
		}
		claimSummaryPage.clickIncidentLink();

		incidentPage.fillLossData(subLossType, weatherRelated);

		if (paymentKey.trim().equalsIgnoreCase("EFT")) {
			incidentPage.clickPartyListLink();

			partyListPage.selectPartyRecord(policyHolderName);

			bankACNumber = Generic.generateRandomString(6, Mode.Numeric);
			partyManagementPage.addBankAC(bankACType, defaultAC, bankACNumber, routingNumber);
		}
		partyManagementPage.clickManageReservesLink();

		manageReservesPage.clickFlowEdit();
		createReserveFor = policyHolderName;
		if (tpClaimType.trim().equals("Y") || tpCoverage.trim().equals("Y")) {
			createReserveFor = tpFirstName + " " + tpLastName;
		}
		manageReservesPage.selectClaimantLossRecord(createReserveFor, reserveType);
		enteredReserveAmount = manageReservesPage.enterAndGetReserveAmount(reserveAmount);
		manageReservesPage.clickLogPaymentLink();

		logPaymentPartyPage.selectParty(createReserveFor);
		logPaymentPartyPage.clickFlowNext();

		logPaymentAnalysisPage.fillPaymentAnalysisData(riskAddressWithCommas, cvrgDdlVal, reserveType, paymentCode, payAmount, deductibleAmount, paymentType);

		logPaymentPayeePage.selectPayeeRadioBtn(policyHolderName);
		logPaymentPayeePage.clickFlowNext();

		logPaymentAddressPage.selectSelectedPayeeRecord(policyHolderName);
		logPaymentAddressPage.selectPayeeFristAddressRecord();
		logPaymentAddressPage.clickFlowNext();

		if (paymentKey.trim().equalsIgnoreCase("check")) {
			logPaymentClaimPaymentPage.selectDeliveryMethod(deliveryMethod);
		}
		logPaymentClaimPaymentPage.selectPaymentMethod(paymentMethod);
		logPaymentClaimPaymentPage.selectRecapLetter(recapLetter);
		logPaymentClaimPaymentPage.clickFlowNext();

		logPaymentSummaryPage.clickFlowFinished();

		Assert.assertTrue(financialSummaryPage.isFinancialSummaryDisplayed(), "Payment process failed");

		//Click on logout
		loginPage.logout();
	}


}
