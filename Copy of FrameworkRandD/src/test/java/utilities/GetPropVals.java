package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Reporter;


public class GetPropVals {
	public String getPropValue(String key) {

		String result = "";
		String propFileName = "";
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			propFileName = "./data/config.properties";

			inputStream = new FileInputStream(propFileName);
			//inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
//			inputStream = getClass().getResourceAsStream(propFileName);
			
			prop.load(inputStream);
			

			//Date time = new Date(System.currentTimeMillis());

			// get the property value and print it out
			result = prop.getProperty(key);
			//String MOUrl = prop.getProperty("MOUrl");
			//String DDUrl = prop.getProperty("DDUrl");
			//String company3 = prop.getProperty("company3");

			//result = "URL List = " + SITUrl + ", " + MOUrl + ", " + DDUrl;
			//System.out.println(result + "\nProgram Ran on " + time);
			return result;
		} catch (IOException e) {
			Reporter.log("<p><font face='verdana' color='red'>Error: Failed to get property value for key '" + key + "' from file '" + propFileName + "'.</font>");
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
//		finally {
//			if (inputStream != null) {
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		return result;
	}
}
