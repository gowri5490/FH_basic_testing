package CorporateWellness;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserDetails {
	public static void main(String args[])
	{
		WebDriver driver=new ChromeDriver();
		//Declaration of Explicit wait
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
				
		 driver.get("https://www.practo.com/search/hospitals?results_type=hospital&q=%5B%7B%22word%22%3A%22hospital%22%2C%22autocompleted%22%3Atrue%2C%22category%22%3A%22type%22%7D%5D&city=Bangalore");
		 
		 driver.manage().window().maximize();
		 
		 WebElement wellness_plan=driver.findElement(By.xpath("//span[text()='Wellness Plans']"));
		 
		 wellness_plan.click();
		 
		 Set<String>windows=driver.getWindowHandles();
		 List<String> windowId=new ArrayList<>(windows);
		 driver.switchTo().window(windowId.get(1));
		 
		 //Test case8: Corporate Wellness form
		 WebElement user_name=driver.findElement(By.xpath("//header[@id='header']//input[@id='name']"));
		 user_name.sendKeys("Kavin");
		 
		 WebElement organization_name=driver.findElement(By.xpath("//header[@id='header']//input[@id='organizationName']"));
		 organization_name.sendKeys("KMCH");
		 
		 WebElement contact_no=driver.findElement(By.xpath("//header[@id='header']//input[@id='contactNumber']"));
		 contact_no.sendKeys("9876543210");
		 
		 WebElement official_emailId=driver.findElement(By.xpath("//header[@id='header']//input[@id='officialEmailId']"));
		 official_emailId.sendKeys("kavinsrg@gmail.com");
		 
		 WebElement organization_size=driver.findElement(By.xpath("//header[@id='header']//select[@id='organizationSize']"));
		 Select org_size=new Select(organization_size);
		 
		 org_size.selectByVisibleText("501-1000");
		 
		 WebElement InterestedIn=driver.findElement(By.xpath("//header[@id='header']//select[@id='interestedIn']"));
		 Select interest=new Select(InterestedIn);
		 
		 interest.selectByVisibleText("Taking a demo");
		 
		 WebElement submit_button=driver.findElement(By.xpath("//header[@id='header']//button[@type='submit']"));
		 
		 if(submit_button.isEnabled())
		 {
			 submit_button.click();
			 System.out.println("user details are valid");
		 }
		 else
		 {
			 System.out.println("User Details are not valid");
		 }
		 
		 //Test case9:Confirmation message
		 WebElement txt_confirm=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ReactModalPortal'][1]/div/div/div/div[1]")));
		 
		 String confirm_msg=txt_confirm.getText();
		 
		 if(confirm_msg.equals("THANK YOU"))
		 {
			 System.out.println("Confirm msg valid");
		 }
		 else
		 {
			 System.out.println("Confirm msg not vallid");
		 }
		 
		  
}
}
