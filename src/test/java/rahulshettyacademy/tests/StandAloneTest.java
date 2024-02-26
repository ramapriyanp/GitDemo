package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

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


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub 
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("ramapriyan.p@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Manas11*");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//below is for waiting until loader icon is seen
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//driver.findElement(By.className("btn")).click();
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector(".action__submit")));
		System.out.println(driver.findElement(By.cssSelector(".action__submit")).isDisplayed());
		

		js.executeScript("window.scrollBy(0,100)");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		

	}

}
