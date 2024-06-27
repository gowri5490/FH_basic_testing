package AllFunctionalities;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class FindDoctorsElements {
	public static void main(String args[]) throws InterruptedException
	{
		WebDriver driver=new EdgeDriver();
		
		driver.get("https://www.practo.com/search/hospitals?results_type=hospital&q=%5B%7B%22word%22%3A%22hospital%22%2C%22autocompleted%22%3Atrue%2C%22category%22%3A%22type%22%7D%5D&city=Bangalore");
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		
		driver.manage().window().maximize();
		
		//Test case10:Validate Find Doctors functionality
		WebElement findDoc_element=driver.findElement(By.xpath("//div[contains(text(),'Find Doctors')]"));
		findDoc_element.click();
		
		WebElement search_speciality=driver.findElement(By.xpath("//input[@placeholder='Search doctors, clinics, hospitals, etc.']"));
		search_speciality.sendKeys("Dentist");
		Thread.sleep(3000);
		
		WebElement recent_search=wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='c-omni-suggestion-item']/span/div[text()='Dentist']"))));
		recent_search.click();
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1800)","");
		
		//Get the all doctors name
		List<WebElement> doctors_name=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[@class='doctor-name']")));
		
		List<WebElement> occupation_type=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='u-d-flex']/span")));
		
		for(int i=0;i<doctors_name.size();i++)
		{
			String doctor_occupation=occupation_type.get(i).getText();
			
			if(doctor_occupation.equals("Dentist"))
			{
				System.out.println("Doctor Name: "+doctors_name.get(i).getText()+" - "+doctor_occupation);
			}
			else
			{
				System.out.println("Occupation Mismatched");
			}
			
		}
		
		//Test case11: Validate Book Free Clinic Visit
		List<WebElement> bookfree_buttons=driver.findElements(By.xpath("//button[@class='u-t-capitalize u-bold u-round-corner--large c-btn--dark-medium']"));
		boolean result=false;
		for(WebElement button:bookfree_buttons)
		{
		if(button.isEnabled())
			result=true;
		else
		{
			result=false;
			break;
		}
		}
		if(result==true)
			System.out.println("BookFree button is present");
		else
			System.out.println("BookFree button is not present");
		driver.navigate().back();
		driver.navigate().back();
		
		
	}

}
