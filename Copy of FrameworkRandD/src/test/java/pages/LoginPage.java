package pages;

import loggerFactory.TestLogger;

import org.openqa.selenium.By;
import org.testng.Reporter;

import utilities.GetPropVals;

public class LoginPage extends BasePage {

	// object locators
	private By userNameLocator = By.id("username");
	private By passwordLocator = By.id("password");
	private By loginButtonLocator = By.id("idLoginButton");
	private GetPropVals getPropVal = new GetPropVals();
	//private String Env = GetPropVal.getPropValue("Env");
	//private String Envi = GetPropVal.getPropValue();//"MO";


	public String getEnvUrl(String Env)
	{
		String URL = "";
		switch (Env) {
		case "QA":
		case "SIT":
			URL = "http://172.27.85.89:9080/Jasper/";
			break;
		case "UAT":
		case "MO":
			URL = "http://172.27.85.85:9080/Jasper/";
			break;
		}
		return URL;
	}

	//Navigate to URL
	public void openApplication()
	{
		try{
			//String env = GetPropVal.getPropValue("Env");
			String env = System.getProperty("Environment");//GetPropVal.getPropValue("Env");
			
			//Reporter.log("<p>==> Starting this test on environment: " + env);
			if (env == null) {
				//Reporter.log("<p><font face='verdana' color='red'> **** WARNING: No Environment has been passed from Jenkins **** </font>");
				env = getPropVal.getPropValue("Env");
				TestLogger.log("<p><font face='verdana' color='red'> **** WARNING: No environment has been passed from Jenkins. Tests will be executed on the default environment: '" + env + "' from properties file **** </font>");
				//Reporter.log("<p><font face='verdana' color='red'> **** WARNING: No environment has been passed from Jenkins. Tests will be executed on the default environment: '" + env + "' from properties file **** </font>");
				}
			TestLogger.log("<p><font face='verdana' color='BlueViolet'>=> Starting this test on environment: " + env + ".</font>");
			String envUrl = getPropVal.getPropValue(env + "Url");
			TestLogger.log("<p><font face='verdana' color='BlueViolet'>=> Navigating to URL: " + envUrl + ".</font>");
			//Reporter.log("<p>==> Navigating to URL: " + envUrl);
			//String envUrl = "MO";
			//driver.get().navigate().to(getEnvUrl(envUrl));//wowuc
			driver.get().navigate().to(envUrl);
			//    	driver.navigate().to(getEnvUrl(Envi));//wowc
		} catch (Exception e) {
			TestLogger.log("F", "<p><font face='verdana' color='red'>Error: Failed to open page.</font>");
			//Reporter.log("<p><font face='verdana' color='red'>Error: Failed to open page.</font>");
			throw (e);
		}
	}

	public void enterUserName(CharSequence... userName)
	{
		writeStepToTestNGReport("Enter user name");
		driver.get().findElement(userNameLocator).sendKeys(userName);//wowuc
		//System.out.printf("@ Entering the text '%s' in '%s'.\n", userName[0], userNameLocator.toString());
		//    	driver.findElement(userNameLocator).sendKeys(userName);//wowc
	}

	public void enterPassword(String pwd)
	{
		writeStepToTestNGReport("Enter password");
		driver.get().findElement(passwordLocator).sendKeys(pwd); //wowuc
		//    	driver.findElement(passwordLocator).sendKeys(pwd);//wowc
	}

	public void clickLogin()
	{
		writeStepToTestNGReport("Click Login button");
		driver.get().findElement(loginButtonLocator).click();//wowuc
		//    	driver.findElement(loginButtonLocator).click();//wowc
	}

	public boolean isUsernameDisplayed()
	{
		writeStepToTestNGReport("Check if User Name field is displayed");
		return driver.get().findElement(userNameLocator).isDisplayed();//wowuc
		//        return driver.findElement(userNameLocator).isDisplayed();//wowc
	}

	public void login(String userName, String pwd) {
		//Auto-generated method stub
		openApplication();
		enterUserName(userName);
		enterPassword(pwd);
		clickLogin();
	}
}
