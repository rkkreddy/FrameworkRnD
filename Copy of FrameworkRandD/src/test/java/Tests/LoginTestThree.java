package Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.BasePage;
//@Listeners({ Tests.TLRemoteScreenShotOnFailure.class })
public class LoginTestThree extends BaseTest {
	@Test(enabled = true, description="Login Test Three")
	public void verifyLoginLogoutThree()//String userName, String password)
    {
        //Navigate to URL
        loginPage.openApplication();

        //Enter user name
        loginPage.enterUserName("LoadTest3");

        //Enter password
        loginPage.enterPassword("homesite");

        //Click login button
        loginPage.clickLogin();

        //Check if signout link is displayed
        Assert.assertTrue(personalInboxPage.isSignoutDisplayed());

        //
        
        //Click on logout
        personalInboxPage.logout();

        //Check if user name field is displayed
        //Assert.IsTrue(loginPage.IsUsernameDisplayed());
    }

}
