package driverFactory;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import loggerFactory.TestLogger;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import driverFactory.CustomRemoteWebDriver;
import utilities.GetPropVals;


public class CustomWebDriver implements WebDriver {

	public CustomRemoteWebDriver driver;
	//public CustomRemoteWebDriver rDriver;
	public int implicitWait;
	private GetPropVals getPropVal = new GetPropVals();
	//public ThreadLocal<Logger> logger = new ThreadLocal<Logger>();

	//    public CustomWebDriver()
	//    {
	//        implicitWait = 60;
	//        driver = DriverProvider.InstantiateDriver();
	//        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS); // change according to wait time. control it from app cofig - done
	//        driver.manage().window().maximize();
	//        //return null;
	//    }
	//	public static WebDriver getDriverInstance() {
	//		return driver;
	//	}
	public String getSeleniumGridNodeURL() {
		String gridHubName = getPropVal.getPropValue("SeleniumGRIDHubName"), gridHubPortStr = getPropVal.getPropValue("SeleniumGRIDHubPort");
		int gridHubPort = Integer.parseInt(getPropVal.getPropValue("SeleniumGRIDHubPort"));

		String proxyID;
		try {
			HttpHost host = new HttpHost(gridHubName, gridHubPort); 
			HttpClient client = HttpClientBuilder.create().build();
			//DefaultHttpClient client = new DefaultHttpClient();
			URL testSessionApi = new URL("http://" + gridHubName + ":" + gridHubPortStr + "/grid/api/testsession?session=" +  driver.getSessionId()); 
			BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST", testSessionApi.toExternalForm()); 
			HttpResponse response  = client.execute(host,r);
			JSONObject object = (JSONObject)new JSONParser().parse(EntityUtils.toString(response.getEntity()));     
			proxyID = (String) object.get("proxyId");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return proxyID;
	}
	public static String getHostName(String ipAddress) {
		try {
			InetAddress inetAddress =InetAddress.getByName(ipAddress);//get the host Inet using ip
			return inetAddress.getHostName();//display the host
		} catch(UnknownHostException uhe) {
			uhe.printStackTrace();
			return "";
		}
		
	}
	
	public String extractIPAddress(String URLString) {
		String IPADDRESS_PATTERN = 
		        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

		Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(URLString);
		        if (matcher.find()) {
		            return matcher.group();
		        }
		        else{
		            return "0.0.0.0";
		        }
	}

	public CustomWebDriver(URL seleniumGridHubUrl, String testClsName) {
		DesiredCapabilities capabilities = null;
		String browser = System.getProperty("Browser");//GetPropVal.getPropValue("Brw");
		String fromJenkins = System.getProperty("FromJenkins");
		if (browser == null) { browser = getPropVal.getPropValue("Brw"); }

		if (browser.equals("FF"))
		{
			capabilities = DesiredCapabilities.firefox();
		} else if (browser.equals("IE"))
		{
			capabilities = DesiredCapabilities.internetExplorer();
			//capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", "C:\\SeleniumServer\\IEDriverServer.exe");
		}
		//Auto-generated constructor stub
		implicitWait = 10;
		//driver = ThreadGuard.protect(new RemoteWebDriver(url, firefox));
		//		driver = new RemoteWebDriver(url, firefox);
		//

		driver = new CustomRemoteWebDriver(seleniumGridHubUrl, capabilities);
		TestLogger.log("i", "<p><font face='verdana' color='BlueViolet'>=> Test started on: " + getHostName(extractIPAddress(getSeleniumGridNodeURL())) + "</font>");
		//Reporter.log("<p>==> <font face='verdana' color='BlueViolet'> ;: " + getSeleniumGridNodeURL() + "</font>");
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS); // change according to wait time. control it from app cofig - done
		driver.manage().window().maximize();
		//logger.set(Logger.getLogger(testClsName));
	}

	public CustomWebDriver() {
		// TODO Auto-generated constructor stub
	}

	public void get(String url) {
		//Auto-generated method stub
		driver.get(url);
	}

	public String getCurrentUrl() {
		//Auto-generated method stub
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		//Auto-generated method stub
		return driver.getTitle();
	}

	public List<WebElement> findElements(By by) {
		//Auto-generated method stub
		//return null;
		//System.out.format("@ Finding element '%s'", by.toString());
		//Log
		//System.out.printf("@ Finding elements '%s'\n", by.toString());//wowuc
		//Thread.currentThread().getId()
		try{
			TestLogger.log("i", "<p><i><font face='verdana' color='chocolate'>==> Finding elements '" + by.toString() + "'.</font></i>");
			//Reporter.log("<p>|    @ Finding elements '" + by.toString() + "'.");//wowuc
			//System.out.printf(MessageFormat.format("@ Finding elements '%s'", by.toString()));
			//List<WebElement> elements = driver.findElements(by);//new List<WebElement>;
			List<WebElement> elements = new ArrayList<WebElement>();
			for (WebElement x : driver.findElements(by))
			{
				elements.add(new CustomWebElement(x));
			}
			//return new System.Collections.ObjectModel.ReadOnlyCollection<IWebElement>(elements);
			return elements;
		} catch (Exception e) {
			TestLogger.log("F", "<p><font face='verdana' color='red'><b>Error: Failed to find elements '" + by.toString() + "'.</b></font>");
			//Reporter.log("<p><font face='verdana' color='red'><b>Error: Failed to find elements '" + by.toString() + "'.</b></font>");
			reportScreenShot();
			throw (e);
		}
	}

	public WebElement findElement(By by) {
		//Auto-generated method stub
		//return null;
		//System.out.format("@ Finding element '%s'", by.toString());
		//System.out.printf("@ Finding the element '%s'\n", by.toString());//wowuc

		try {
			TestLogger.log("i", "<p><i><font face='verdana' color='chocolate'>==> Finding element '" + by.toString() + "'.</font></i>");
			//Reporter.log("<p>|    @ Finding the element '" + by.toString() + "'.");
			//System.out.printf(MessageFormat.format("@ Finding element '%s'", by.toString()));
			return new CustomWebElement(driver.findElement(by));
		} catch (Exception e) {
			TestLogger.log("F", "<p><font face='verdana' color='red'><b>Error: Failed to find element '" + by.toString() + "'.</b></font>");
			//Reporter.log("<p><font face='verdana' color='red'><b>Error: Failed to find element '" + by.toString() + "'.</b></font>");
			reportScreenShot();
			throw(e);
		}
	}

	public String getPageSource() {
		//Auto-generated method stub
		return driver.getPageSource();
	}

	public void close() {
		//Auto-generated method stub
		driver.close();

	}

	public void quit() {
		//Auto-generated method stub
		driver.quit();

	}

	public Set<String> getWindowHandles() {
		//Auto-generated method stub
		return driver.getWindowHandles();
	}

	public String getWindowHandle() {
		//Auto-generated method stub
		return driver.getWindowHandle();
	}

	public TargetLocator switchTo() {
		//Auto-generated method stub
		return driver.switchTo();
	}

	public Navigation navigate() {
		//Auto-generated method stub
		return driver.navigate();
	}

	public Options manage() {
		//Auto-generated method stub
		return driver.manage();
	}

	public void reportScreenShot() {
		File scrFile = null;
		String destDir = null;
		String destDir2 = null;
		String destFile = null;
		if (driver != null) {
			try {
				scrFile = driver.getScreenshotAs(OutputType.FILE);
				DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
				destDir = "target/surefire-reports/screenshots";
				destDir2 = "test-output/html/error-screenshots";
				new File(destDir2).mkdirs();
				destFile = dateFormat.format(new Date()) + ".png";
			} catch (WebDriverException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				FileUtils.copyFile(scrFile, new File(destDir2 + "/" + destFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Reporter.setEscapeHtml(false);
			//Reporter.log(destFile + " - Screenshot");
			//Reporter.log("<p>Screenshot saved - <a href='../ws/target/surefire-reports/screenshots/"  + destFile + "' hight='100' width='100'/> </a>");
			//Reporter.log("<p>Screenshot saved - <a href=../ws/target/surefire-reports/screenshots/" + destFile + ">Screenshot</a>");
			TestLogger.log("F", "<p><font face='verdana' color='red'>ERROR SCREENSHOT:" + "<br><br></font><a href=./error-screenshots/" + destFile + " target='_blank'><img src='./error-screenshots/" + destFile + "' alt='Error Snapshot' style='width:304px;height:228px'></a>");
			//Reporter.log("<p><font face='verdana' color='red'>ERROR SCREENSHOT:" + "<br><br></font><a href=./screenshots/" + destFile + " target='_blank'><img src='./screenshots/" + destFile + "' alt='Error Snapshot' style='width:304px;height:228px'></a>");
			//Reporter.log("<p><font face='verdana' color='red'>SCREENSHOT SAVED - " + "'.</font><a href=./screenshots/" + destFile + " target='_blank'>click here</a>")
		}
	}
}
