package Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pages.BasePage;
//@Listeners({ Tests.TLRemoteScreenShotOnFailure.class })
public class LoginTestFour extends BaseTest {
	@Test(enabled = true, description="Login Test Four")
	public void verifyLoginLogout()//String userName, String password)
    {
		//ExtentReports extent = ExtentManager.getInstance();
        //Navigate to URL
        loginPage.openApplication();

        //Enter user name
        loginPage.enterUserName("LoadTest4");

        //Enter password
        loginPage.enterPassword("homesite");

        //Click login button
        loginPage.clickLogin();

        //Check if signout link is displayed
        Assert.assertTrue(personalInboxPage.isSignoutDisplayed());

        //
        
        //Click on logout
        loginPage.logout();

        //Check if user name field is displayed
        //Assert.IsTrue(loginPage.IsUsernameDisplayed());
        Reporter.getOutput();
    }

}
