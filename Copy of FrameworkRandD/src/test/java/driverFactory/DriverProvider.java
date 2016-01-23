//package driverFactory;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//public class DriverProvider {
//	public static WebDriver driver;
//	// TODO add the below URI to properties file and get it here
//	public static String hubAddrs = "http://hswebdev123:4444/wd/hub";
//    public static WebDriver InstantiateDriver()
//    {
//            //if (driver == null)
//            //{
//                driver = getDriver();
//            //}
//            return driver;
//
//            //Generic.stepNo = 1;
//     }
//	private static WebDriver remoteDriver() throws MalformedURLException {
//		//Auto-generated method stub
//		return new RemoteWebDriver(new URL(hubAddrs), DesiredCapabilities.firefox());
//	}
//	private static WebDriver getDriver() {
//		//Auto-generated method stub
////		if (ConfigurationManager.AppSettings["execLocation"] == "LOCAL")
////        {
////            driver = localDriver();//driver = ; //make different functions/methods
////
////        }
////        else
////        {
//	
//			try {
//				driver = remoteDriver();
//			} catch (MalformedURLException e) {
//				//Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("Check Selenium Grid URI");		}
//		
//             //new RemoteWebDriver(new Uri(ConfigurationManager.AppSettings["SeleniumGridHubUri"]), DesiredCapabilities.Firefox());
////        }
//        return driver;
//		
//	}
//	public static void QuitDriver()
//    {
//        driver.quit();
//        driver = null;
//    }
//
//}
