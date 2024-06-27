package AllFunctionalities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SurgeriesFunction {
	public static void main(String args[]) throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.practo.com/search/hospitals?results_type=hospital&q=%5B%7B%22word%22%3A%22hospital%22%2C%22autocompleted%22%3Atrue%2C%22category%22%3A%22type%22%7D%5D&city=Bangalore");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		
		WebElement surgeries_element=driver.findElement(By.xpath("//div[text()='Surgeries']"));
		surgeries_element.click();
		
		//Test case16: Validate PractoCare
		WebElement care_no=driver.findElement(By.xpath("//div[@class='flex items-center gap-12px']/h1"));
		String p_no=care_no.getText();
		
		if(p_no.matches("^[0-9]+$")&&p_no.length()==11)
		{
			System.out.println("Valid mobile number");
		}
		else
		{
			System.out.println("Mobile number not valid");
		}
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600);","");
		
		//Test case17: Validate popular surgeries
		List<WebElement> surgeries_list=driver.findElements(By.xpath("//p[@class='mt-12px AilmentItem-module_itemText__XvCHL']"));
		System.out.println("===========================");
		System.out.println("Popular Surgeries");
		for(WebElement surgery:surgeries_list)
		{
			System.out.println(surgery.getText());
		}
		
		//Test case18: validate Our departments
		List<WebElement> departments=driver.findElements(By.xpath("//h1[@class='text-module_base__1vdUh text-16px font-bold text-black']"));
		System.out.println("===========================");
		System.out.println("Our Departments");
		for(WebElement department:departments)
		{
			System.out.println(department.getText());
		}
		//Test case19: Validate PractoCare Benefits
		List<WebElement> benifits_element=driver.findElements(By.xpath("//h1[@class='list-module_sizeSmallItemContentTitle__kaSwM list-module_itemContentTitle__XreVD text-gray-2']"));
		System.out.println("===========================");
		System.out.println("Practo Care Benefist");
		for(WebElement benefit:benifits_element)
		{
			System.out.println(benefit.getText());
		}
		
		//Test case20: Validate Book your consultation
		WebElement select_Ailment=driver.findElement(By.xpath("//div[@class='generalLeadForm-module_selectors__oNbtj']/descendant::span[@class='generalLeadForm-module_icon__jt4Z-']"));
		select_Ailment.click();
		
		WebElement select_radio=driver.findElement(By.xpath("(//span[@class='Radio-module_radioCheckmark__BpOn-'])[23]"));
		select_radio.click();
		
		WebElement consultant_name=driver.findElement(By.xpath("//input[@id='Name-Gen-Lead-Form']"));
		consultant_name.sendKeys("Kavin");
		String value="Kavin";
		boolean con_result=true;
		if(value.matches("^[0-9]+$"))
		{
			WebElement invalid_nameMsg=driver.findElement(By.xpath("//div[@class='text-10px text-red-1 absolute' and contains(text(),'Name')]"));
			System.out.println(invalid_nameMsg.getText());
			con_result=false;
		}
		WebElement consultant_pno=driver.findElement(By.xpath("//input[@id='Phone-Gen-Lead-Form']"));
		consultant_pno.sendKeys("9876543210");
		String con_pno="9876543210";
		if(con_pno.matches("^[a-z A-Z]+$")||con_pno.length()!=10)
		{
			
			WebElement invalid_pnoMsg=driver.findElement(By.xpath("//div[@class='text-10px text-red-1 absolute' and contains(text(),'Number')]"));
			System.out.println(invalid_pnoMsg.getText());
			Thread.sleep(2000);
			con_result=false;
		}
		if(con_result==true)
		{
			WebElement submit_button=driver.findElement(By.xpath("//button[@class='generalLeadForm-module_submit-cta__7eVQe']"));
			submit_button.click();
			
			WebElement con_confirmMsg=driver.findElement(By.xpath("//p[@class='pt-40px']"));
			System.out.println(con_confirmMsg.getText());
		}
		else
		{
			System.out.println("invalid details");
		}
		
		
		
	}

}
