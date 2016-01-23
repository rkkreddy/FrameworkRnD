package driverFactory;

import java.util.List;
//import java.util.logging.Logger;







import loggerFactory.TestLogger;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class CustomWebElement implements WebElement {

	public WebElement iElement;
	
	//public Logger logger;
    public CustomWebElement(WebElement element)
    {
        iElement = element;
        //logger = logg;
    }
    
	public void click() {
		final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
		System.setProperty(ESCAPE_PROPERTY, "false");
		//Auto-generated method stub
		TestLogger.log("<p><i><font face='verdana' color='darkolivegreen'>===> Clicking the element.</font></i>");
		//Reporter.log("<p>|      -> Clicking the element.");
		//System.out.println("  -> Clicking the element");//wowuc
		try {
			iElement.click();
		} catch (ElementNotVisibleException enve) {
			// TODO Auto-generated catch block
			enve.printStackTrace();
		}
		
	}

	public void submit() {
		//Auto-generated method stub
		iElement.submit();
		
	}

	public void sendKeys(CharSequence... keysToSend) {//(CharSequence... keysToSend) {
		final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
		System.setProperty(ESCAPE_PROPERTY, "false");
		//Auto-generated method stub
		//System.out.printf("  -> Entering value '%s'\n", keysToSend[0]);//wowuc
		TestLogger.log("<p><i><font face='verdana' color='darkolivegreen'>===> Entering value '" + keysToSend[0] + "'.</font></i>");
		//Reporter.log("<p>|      -> Entering value '" + keysToSend[0] + "'.");
		iElement.sendKeys(keysToSend);
	}

	public void clear() {
		//Auto-generated method stub
		iElement.clear();
	}

	public String getTagName() {
		//Auto-generated method stub
		return iElement.getTagName();
	}

	public String getAttribute(String name) {
		//Auto-generated method stub
		return iElement.getAttribute(name);
	}

	public boolean isSelected() {
		//Auto-generated method stub
		return iElement.isSelected();
	}

	public boolean isEnabled() {
		//Auto-generated method stub
		return iElement.isEnabled();
	}

	public String getText() {
		//Auto-generated method stub
		return iElement.getText();
	}

	public List<WebElement> findElements(By by) {
		//Auto-generated method stub
		return iElement.findElements(by);
	}

	public WebElement findElement(By by) {
		//Auto-generated method stub
		return iElement.findElement(by);
	}

	public boolean isDisplayed() {
		//Auto-generated method stub
		return iElement.isDisplayed();
	}

	public Point getLocation() {
		//Auto-generated method stub
		return iElement.getLocation();
	}

	public Dimension getSize() {
		//Auto-generated method stub
		return iElement.getSize();
	}

	public String getCssValue(String propertyName) {
		//Auto-generated method stub
		return iElement.getCssValue(propertyName);
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target)
			throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

}
