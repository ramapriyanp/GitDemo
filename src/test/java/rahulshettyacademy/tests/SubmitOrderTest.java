package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";

	@Test
	public void submitOrder() throws IOException, InterruptedException
	
	{
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("ramapriyan.p@gmail.com", "Manas11*");
		
		List<WebElement>products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectcountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmessage= confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(confirmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("ramapriyan.p@gmail.com", "Manas11*");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyProductDisplay(productName));	
	}
	
}
