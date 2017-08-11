import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestReport 
{ 
	//����� ������ �������� ��������
	ExtentReports report;
	ExtentTest logger; 
	WebDriver driver;
	String response;
	Boolean flag=true;
	String title;
 
 
@Test
public void verifyBlogTitle() throws IOException
{
	//����� ����
	report=new ExtentReports("C:\\Report\\LearnAutomation1.html");
	logger=report.startTest("Test Report DELL");

	//����� ������- ����� ��� ���� ����- ���� ����� ������ ��� ����� ����� ��� �����
	String url_s="http://www.kljfffff..com";	
	URL url = new URL(url_s);
	logger.log(LogStatus.INFO, "Browser started ");
	logger.log(LogStatus.INFO, "Application is up and running-SITE 1");
	HttpURLConnection connection = (HttpURLConnection)		url.openConnection();
	try
	{
		connection.connect();
	    response = connection.getResponseMessage();	          		    
	    connection.disconnect();
	}
	catch(Exception exp)
	{
		flag=false;
	}	
	if(!flag)
	{
		logger.log(LogStatus.ERROR, "Error SITE 1");
	}
	else
		logger.log(LogStatus.PASS, "Title verified SITE 1");
	
	
	
	//����� ����� - ���� ����� ���� ���� ������� 
	driver=new FirefoxDriver();
	driver.get("http://www.ynet.co.il/home/0,7340,L-8,00.html");
	logger.log(LogStatus.INFO, "Application is up and running-SITE 2");
    driver.findElement(By.linkText("��� �����")).click();
    title=driver.getTitle();
    Assert.assertTrue(title.contains("ynet"));
	logger.log(LogStatus.PASS, "Title verified SITE 2");
	
	
	// ����� ������ - ���� ����� ������ ������ �� ����� ���� ��� �����.
	driver.get("http://www.Google.com");
	logger.log(LogStatus.INFO, "Application is up and running-SITE 3");
	String title=driver.getTitle();
	driver.findElement(By.id("lst-ib")).sendKeys("����");
	driver.findElement(By.name("btnK")).click();
	if(!title.contains("fg"))
	{
		flag=false;
	}
	else
		logger.log(LogStatus.PASS, "Title verified");
}
 
 //���� ���� ����� ������ - ����� ����� ���� ����� ������
@AfterMethod
public void tearDown(ITestResult result)
{
	if(result.getStatus()==ITestResult.FAILURE||flag==false)
	{
		logger.log(LogStatus.ERROR, "ERROR SITE 3");
	}
	report.endTest(logger);
	report.flush();
}
}