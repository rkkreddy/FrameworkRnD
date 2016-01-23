package Tests;

import org.testng.Assert;
import org.testng.ITest;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import utilities.XLUtility;


public class LoginTest extends BaseTest implements ITest{
	
	private String testName;
	private String uName;
    private String pwd;
    //private GetPropVals GetPropVal = new GetPropVals();
	@Factory (dataProvider = "dataFromXL") //(dataProviderClass = dataProvider.class, dataProvider = "dataFromXL")
	public LoginTest(String tstName, String userName, String password)
	{
		this.testName = tstName;
		this.uName = userName;
		this.pwd = password;
	}
	
	@BeforeMethod
	public void getTName()
	{
		getTestName();
	}
	@Override
	public String getTestName() {
		// TODO Auto-generated method stub
		//StringBuilder builder = new StringBuilder();
        //builder.append("[name=").append(uName).append("]");//.append(", age=").append(age).append("]");
        //return builder.toString();
		return testName + "(" + uName + ")";
	}
	
	@DataProvider(name = "dataFromXL", parallel = true)
	public static Object[][] xlData() throws Exception{
		
		return XLUtility.getTableArray("./data/TestData.xlsx", "Sheet1");
		
	}
	
	@Test//(dataProviderClass = dataProvider.class, dataProvider = "userKeys")//, threadPoolSize = 5)
	public void verifyLoginLogout()//(String userName, String password)
    {
		//try{
		Reporter.log("<p>***** Log for - <b>" + testName + " *****</b>");
		//System.out.println(GetPropVal.getPropValue("SITUrl"));
        //Navigate to URL
        loginPage.openApplication();

        //Enter user name
        loginPage.enterUserName(uName);

        //Enter password
        loginPage.enterPassword(pwd);

        //Click login button
        loginPage.clickLogin();

        //Check if signout link is displayed
        Assert.assertTrue(personalInboxPage.isSignoutDisplayed());
        
        //Click on logout
        loginPage.logout();

        //Check if user name field is displayed
        //Assert.IsTrue(loginPage.IsUsernameDisplayed());
		//}
		//catch (Exception e) {
		//	Reporter.log("<p> Cause: " + e.getCause() + "<p> Message: " + e.getMessage());
		//}
    }

}
