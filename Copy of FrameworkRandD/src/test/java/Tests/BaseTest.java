package Tests;

import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.xml.XmlTest;

import pages.BasePage;
import pages.ClaimSummaryPage;
import pages.FinancialSummaryPage;
import pages.IncidentPage;
import pages.LogPaymentAddressPage;
import pages.LogPaymentAnalysisPage;
import pages.LogPaymentClaimPaymentPage;
import pages.LogPaymentPartyPage;
import pages.LogPaymentPayeePage;
import pages.LogPaymentSummaryPage;
import pages.LoginPage;
import pages.ManageReservesPage;
import pages.ManageSIUReferralUpdateScorePage;
import pages.PartyListPage;
import pages.PartyManagementPage;
import pages.PersonalInboxPage;
import pages.SiuSpotlightPage;
import pages.SearchPage;
import pages.NewClaimSetUpPage;
import pages.NewClaimPolicyPage;
import pages.NewClaimIncidentPage;
import pages.NewClaimPartyPage;
import pages.NewClaimItemManagementPage;
import pages.NewClaimSummaryPage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import loggerFactory.ExtentManager;
import loggerFactory.ExtentTestManager;

import com.relevantcodes.extentreports.ExtentReports;
 
import com.relevantcodes.extentreports.LogStatus;

//@Listeners({ Tests.TLRemoteScreenShotOnFailure.class })
public class BaseTest extends XmlTest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public XmlTest xmlTst;
	@Override
	public void setName(String testName) {
		xmlTst.setName(testName);
	}
	//public static WebDriver driver;
    public static int implicitWait;
//    public static String hubAddrs = "http://hswebdev123:4444/wd/hub";
    //public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
//    public WebDriver StartWebDriver()
//    {
//        implicitWait = 60;
//        driver = DriverProvider.InstantiateDriver();
//        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS); // change according to wait time. control it from app cofig - done
//        driver.manage().window().maximize();
//        return driver;
//    }
//    
	public LoginPage loginPage;
	public PersonalInboxPage personalInboxPage;
	public SearchPage searchByPage;
	public NewClaimSetUpPage newClaimSetUpPage;
	public NewClaimPolicyPage newClaimPolicyPage;
	public NewClaimIncidentPage newClaimIncidentPage;
	public NewClaimPartyPage newClaimPartyPage;
	public NewClaimItemManagementPage newClaimItemManagementPage;
	public NewClaimSummaryPage newClaimSummaryPage;
	public ClaimSummaryPage claimSummaryPage;
	public IncidentPage incidentPage;
	public PartyListPage partyListPage;
	public PartyManagementPage partyManagementPage;
	public ManageReservesPage manageReservesPage;
	public LogPaymentPartyPage logPaymentPartyPage;
	public LogPaymentAnalysisPage logPaymentAnalysisPage;
	public LogPaymentPayeePage logPaymentPayeePage;
	public LogPaymentAddressPage logPaymentAddressPage;
	public LogPaymentClaimPaymentPage logPaymentClaimPaymentPage;
	public LogPaymentSummaryPage logPaymentSummaryPage;
	public FinancialSummaryPage financialSummaryPage;
	public ManageSIUReferralUpdateScorePage manageSIUReferralUpdateScorePage;
	public SiuSpotlightPage siuSpotlightPage;
	
	//************************************************
	protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
	public static ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }
	//************************************************
	//ExtentReports extent = ExtentReports.get(BaseTest.class);		
	@BeforeMethod//(alwaysRun = true)
	/*public void startExtent(Method method) {
        ExtentTestManager.startTest(method.getName()); // new
    }*/
	
	public void fixtureSetup(Method method) throws MalformedURLException// throws MalformedURLException 
    {
		ExtentTestManager.startTest(method.getName()); // new
		//Reporter.log(">>>>>>>>>>>>> Result starts for: " + method.getName());
		//extent.init("file-path.html", true);
        //Instantiate driver
		//driver = new StartWebDriver();
		//implicitWait = 60;
        //BasePage.InstantiateDriver();
        //driver.set(new RemoteWebDriver(new URL(hubAddrs), DesiredCapabilities.firefox()));
        //driver = new CustomWebDriver();
		//System.out.println("Enterted");
		BasePage.instantiateDriver(this.getClass().getName());
		
        //driver = new RemoteWebDriver(new URL(hubAddrs), DesiredCapabilities.firefox());
        //driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS); // change according to wait time. control it from app cofig - done
       // driver.manage().window().maximize();
        //BasePage.InstantiateDriver();

        //Initialize all the required pages
        loginPage = new LoginPage();
        personalInboxPage = new PersonalInboxPage();
        searchByPage = new SearchPage();
        newClaimSetUpPage = new NewClaimSetUpPage();
        newClaimPolicyPage = new NewClaimPolicyPage();
        newClaimIncidentPage = new NewClaimIncidentPage();
        newClaimPartyPage = new NewClaimPartyPage();
        newClaimItemManagementPage = new NewClaimItemManagementPage();
        newClaimSummaryPage = new NewClaimSummaryPage();
        claimSummaryPage = new ClaimSummaryPage();
        incidentPage = new IncidentPage();
        partyListPage = new PartyListPage();
        partyManagementPage = new PartyManagementPage();
        manageReservesPage = new ManageReservesPage();
        logPaymentPartyPage = new LogPaymentPartyPage();
        logPaymentAnalysisPage = new LogPaymentAnalysisPage();
        logPaymentPayeePage = new LogPaymentPayeePage();
        logPaymentAddressPage = new LogPaymentAddressPage();
        logPaymentClaimPaymentPage = new LogPaymentClaimPaymentPage();
        logPaymentSummaryPage = new LogPaymentSummaryPage();
        financialSummaryPage = new FinancialSummaryPage();
        manageSIUReferralUpdateScorePage = new ManageSIUReferralUpdateScorePage();
        siuSpotlightPage = new SiuSpotlightPage();
    }
	
	
	
	
	
	@AfterMethod //(alwaysRun = true)
	public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));  // new
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));  // new

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);  // new
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");  // new
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));  // new
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");  // new
        }

        ExtentTestManager.endTest();  // new

        extent.flush();
    }
	@AfterMethod
	public void fixtureTearDown(ITestResult result) {
        //DriverProvider.QuitDriver();
        //driver.get().quit();
		//driver.set(null);
        //driver.quit();
        //driver = null;
		boolean rsltStatus = true;
		if (result.getStatus() == ITestResult.FAILURE) {
			rsltStatus = false;
		}
		BasePage.quitDriver(rsltStatus);
    }
	
	

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
