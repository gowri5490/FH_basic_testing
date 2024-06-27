package HomePage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {
	
	public static void main(String args[]) throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		
		//Declaration of Explicit wait
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://practo.com");
		
		//Test case1: Practo logo is visible
		
		WebElement logo_icon=driver.findElement(By.xpath("//span[@class='practo-logo']//i[@class='practo_logo_new']"));
		
		if(logo_icon.isDisplayed())
		{
			System.out.println("Practo logo is displayed");
		}
		else
		{
			System.out.println("Practo logo is not displayed");
		}
		//Test case2: Login button validation
		WebElement login_button=driver.findElement(By.xpath("//a[@class='btn-border nav-login nav-interact ']"));
		System.out.println("===========================================");
		if(login_button.isDisplayed())
		{
			System.out.println("Login button is present");
		}
		else
		{
			System.out.println("Login button is not present");
		}
		//Test case3: Download option validation
		WebElement download_option=driver.findElement(By.xpath("//div[@class='downloads']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",download_option);
		Thread.sleep(2000);
		if(download_option.isDisplayed())
		{
			System.out.println("Download option is present");
		}
		else
		{
			System.out.println("Download option is not present");
		}

		
		//Test case4: Validate Bangalore city hospitals are displayed
		WebElement txt_searchCity=driver.findElement(By.xpath("//input[@placeholder='Search location']"));
		txt_searchCity.click();
		
		WebElement clear_txt=driver.findElement(By.xpath("//i[@class='icon-ic_cross_solid']"));
		clear_txt.click();
		txt_searchCity.sendKeys("Bangalore");
		Thread.sleep(2000);
		
		WebElement city_element=driver.findElement(By.xpath("//div[contains(text(),'Bangalore')]"));
		city_element.click();
		
//		WebElement click_entireCity=driver.findElement(By.xpath("//div[contains(text(),'Search in entire Bangalore')]"));
//		click_entireCity.click();
		
		WebElement txt_searchUserOptions=driver.findElement(By.xpath("//input[@placeholder='Search doctors, clinics, hospitals, etc.']"));
		txt_searchUserOptions.sendKeys("Hospital");
	
		WebElement click_hospital=driver.findElement(By.xpath("//div[@class='c-omni-suggestion-item']//span//div[text()='Hospital']"));
		click_hospital.click();
		Thread.sleep(2000);
		
		//Validate Bangalore city hospital details are displayed
		WebElement city_title=driver.findElement(By.xpath("//h1[@class='title']"));
		String title=city_title.getText();
		
		if(title.contains("Bangalore"))
		{
			System.out.println("Bangalore city hospital diplayed");
		}
		else
		{
			System.out.println("Other city details are displayed");
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1600)", "");
		Thread.sleep(4000);
		
		//Test case5:validate 24/7 opening hospitals displayed
		List<WebElement> hospitalsTimeDetails=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='uv2-spacer--lg-left']")));
		
		int hsptl_size=hospitalsTimeDetails.size();
		List<Integer> hsptls_index=new ArrayList<>();
		
		System.out.println("Total hospitals: "+hsptl_size);
		int j=0;
		for(int i=0;i<hsptl_size;i++)
		{
			String openTime=hospitalsTimeDetails.get(i).getText();
			if(openTime.equals("MON - SUN 00:00AM - 11:59PM"))
				hsptls_index.add(i);
		}
		
		List<WebElement> hsptl_ttle=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='c-estb-card']/descendant::a[1]")));
		System.out.println("===========================================");
		System.out.println("24/7 opening hospitals count: "+hsptls_index.size());
		for(int i=0;i<hsptls_index.size();i++)
		{	
			System.out.println("Hospital Name: "+hsptl_ttle.get(hsptls_index.get(i)).getText());	
		}
		
		//Test case6: validate rating more then
		List<WebElement> ratingDetails=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='text-1']/span[@class='u-bold']")));
		
		int rating_size=ratingDetails.size();
		List<Integer> rating_index=new ArrayList<>();
		
		for(int i=0;i<rating_size;i++)
		{
			Double rating_value=Double.parseDouble(ratingDetails.get(i).getText());
			if(rating_value>3.5)
			{
				rating_index.add(i);
			}
		}
		
		System.out.println("===========================================");
		System.out.println("More than 3.5 rated Hospital");
		for(int i=0;i<rating_index.size();i++)
		{
			System.out.println("Hospital Name: "+hsptl_ttle.get(rating_index.get(i)).getText());
		}
		
	  //Test case7:top cities validation
	List<WebElement> top_cities=driver.findElements(By.xpath("//div[@class='u-grey_3-text']/div/a/h3[starts-with(text(),'Hospitals in')]"));
	System.out.println("===========================================");
	System.out.println("Hospitals in Top Cities");
	for(WebElement city:top_cities)
	{
		System.out.println(city.getText());
	}
	
	
}
}
