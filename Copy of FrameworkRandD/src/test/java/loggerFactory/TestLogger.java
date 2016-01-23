package loggerFactory;

import org.testng.Reporter;

import Tests.BaseTest;

import com.relevantcodes.extentreports.LogStatus;

public class TestLogger extends BaseTest {
    public static void log(final String message) {
          Reporter.log(message + "<br>", true);
          ExtentTestManager.getTest().log(LogStatus.INFO, message + "<br>");  // new
    }
    
    public static void log(final String status, final String message) {
        Reporter.log(message + "<br>", true);
        switch (status.toUpperCase().trim()) {
        case "PASS": ExtentTestManager.getTest().log(LogStatus.PASS, message + "<br>");
        			break;
        case "P": ExtentTestManager.getTest().log(LogStatus.PASS, message + "<br>");
					break;
        case "FAIL" : ExtentTestManager.getTest().log(LogStatus.FAIL, message + "<br>");
        			break;
        case "F" : ExtentTestManager.getTest().log(LogStatus.FAIL, message + "<br>");
					break;
        case "INFO" : ExtentTestManager.getTest().log(LogStatus.INFO, message + "<br>");
					break;
        case "I" : ExtentTestManager.getTest().log(LogStatus.INFO, message + "<br>");
					break;
        default: ExtentTestManager.getTest().log(LogStatus.INFO, message + "<br>");  // new
					break;
        }
        
        
  }
}
