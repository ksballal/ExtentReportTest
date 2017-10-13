package tmp6;

import mypkg.ExtentManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.lang.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TC2 
extends ExtentManager
{
	
	ExtentReports extent1;
	ExtentTest test1;
	WebDriver driver;
	
	@BeforeClass
	public void M1(){
		extent1 = ExtentManager.GetExtent();
//		System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		System.out.println("webdriver.chrome.driver" + "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void checkHome()
	{
		try{
		driver.get("http://www.qavalidation.com/");
		
		//test1 = extent.startTest("OpenUT", "Verify HomePage");//earlier version
		test1 = extent1.createTest("QAVsite", "Verify HomePage");
		
		if(driver.getTitle().contains("QA manual")){
			//test1.log(LogStatus.PASS, driver.getTitle() +" contain "+"QA & Validation" );//earlier version
			//test1.log(Status.PASS, driver.getTitle() +" contain "+"QA & Validation");
			//or
		    test1.pass(driver.getTitle() +" contain "+"QA manual");
		    //test1.log(Status.INFO, "Snapshot" +  test1.addScreenCaptureFromPath("./1.jpg"));
		}
		else
			//test1.log(LogStatus.FAIL, driver.getTitle() +" doesn't contain "+"QA & Validation" );//earlier version
			test1.log(Status.FAIL, driver.getTitle() +" doesn't contain "+"QA manual" );
		}catch(Exception e){test1.log(Status.ERROR, e.getMessage());}
	}
	
	@Test
	public void checkFail(){
		test1 = extent1.createTest("test1ing how fail works");
		//test1.log(Status.INFO, "fail check started");
		AssertJUnit.fail("test1 fail");
	}
		
	@AfterClass
	public void tear()
	{
		//extent.endtest1(test1);//earlier version
		extent1.flush();
		extent1.close();
		driver.quit();
	}
}
