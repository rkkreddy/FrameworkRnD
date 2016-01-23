package Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITest;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import utilities.XLUtility;


public class CopyOfLoginTest extends BaseTest implements ITest {
	
	private String uName;
    private String pwd;
	@Factory (dataProvider = "xlData")
	public CopyOfLoginTest(String scenario, String userName, String password)
	{
		this.uName = userName;
		this.pwd = password;
	}
	
//	@Override
//	public String getTestName() {
//		// TODO Auto-generated method stub
//		StringBuilder builder = new StringBuilder();
//        builder.append("[name=").append(uName).append("]");//.append(", age=").append(age).append("]");
//        return builder.toString();
//	}
	
	@Override
	public String getTestName() {
		// TODO Auto-generated method stub
		//StringBuilder builder = new StringBuilder();
        //builder.append("[name=").append(uName).append("]");//.append(", age=").append(age).append("]");
        //return builder.toString();
		return uName;
	}
	
	//@Override
	public String getTestDescription() {
		// TODO Auto-generated method stub
		//StringBuilder builder = new StringBuilder();
        //builder.append("[name=").append(uName).append("]");//.append(", age=").append(age).append("]");
        //return builder.toString();
		return uName;
	}
	
	@DataProvider(name = "testdata", parallel = true)
    public static Object[][] userCredss() {
        return new Object[][] { { "LoadTest1", "password" }, { "LoadTest2", "password" }, { "LoadTest3", "password" }, { "LoadTest4", "password" } };
    }
	
	@DataProvider(parallel = true)
	public static Object[][] xlData() throws Exception{
		
		return XLUtility.getTableArray("./data/TestData.xlsx", "Sheet1");
//		return XLUtility.getTableArray("C://TFS_SourceControl//QA//Selenium//CMS//Java//CMSMaven//data//TestData.xlsx", "Sheet1");
		
	}
	
	@Test//(threadPoolSize = 3, invocationCount = 1)//(dataProvider = "testdata")//, threadPoolSize = 5)
	public void verifyLoginLogout() throws IOException//(String userName, String password)
    {
		//this.setName(uName);
		System.out.println(this.toString());
		//this.context.setAttribute("name", name);
		Reporter.log("<p>Log for - <b>" + uName + "</b>");
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
    }
	

}
