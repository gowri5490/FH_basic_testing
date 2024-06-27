package AllFunctionalities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
public class VideoConsultFunction {
	public static void main(String args[])
	{
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.practo.com/search/hospitals?results_type=hospital&q=%5B%7B%22word%22%3A%22hospital%22%2C%22autocompleted%22%3Atrue%2C%22category%22%3A%22type%22%7D%5D&city=Bangalore");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		
		
		WebElement videoCon_element=driver.findElement(By.xpath("//div[text()='Video Consult']"));
		videoCon_element.click();
		//Test case12: Validate ConsultNow button
		WebElement consult_button=driver.findElement(By.xpath("//div[@class='content']//a[@class='link primary-button cta'][normalize-space()='Consult Now']"));
		
		if(consult_button.isEnabled())
		{
			System.out.println("Consult Now button is Enabled");
		}
		else
		{
			System.out.println("Consult Now button is not Enabled");
		}
		
		//Test case13: Validate Specialities
		List<WebElement> specialities_element=driver.findElements(By.xpath("//div[@id='TopSpecialityCardsContainer']/descendant::div[@class='content']/h4"));
		System.out.println("===================================");
		System.out.println("Top Specialities");
		for(WebElement Speciality:specialities_element)
		{
			System.out.println(Speciality.getText());
		}
		
		//Test case14: Validate CommonHealthConcerns
		List<WebElement> commonHealthConcerns=driver.findElements(By.xpath("//div[@id='HealthProblemCardsContainer']/descendant::div[@class='content']/h4"));
		List<WebElement> healthConcernPrice=driver.findElements(By.xpath("//div[@id='HealthProblemCardsContainer']/descendant::div[@class='content']/p"));
		System.out.println("===================================");
		System.out.println("Common Health Concerns");
		for(int i=0;i<4;i++)
		{
			System.out.println(commonHealthConcerns.get(i).getText()+" - "+healthConcernPrice.get(i).getText());
		}
		
		//Test case15: Validate Offers
		List<WebElement>offers=driver.findElements(By.xpath("//div[@id='OfferCardsContainer']/descendant::div[@class='content']/h4"));
		System.out.println("===================================");
		System.out.println("Offers");
		for(WebElement offer:offers)
			System.out.println(offer.getText());
	}

}
