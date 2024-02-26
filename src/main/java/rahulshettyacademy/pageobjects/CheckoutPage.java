package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="(//button[@type='button'])[2]")
	//@FindBy(css="[placeholder='Select country']")
	WebElement selectCountry;
	
	By results =By.cssSelector(".ta-results"); 
	
	public void selectcountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() throws InterruptedException
	{	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
		Thread.sleep(5000);
		submit.click();
		return new ConfirmationPage(driver);
	}
	


	
	

}
