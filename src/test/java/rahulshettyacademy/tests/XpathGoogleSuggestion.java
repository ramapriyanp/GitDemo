package rahulshettyacademy.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XpathGoogleSuggestion {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// Set the path to the ChromeDriver executable (you need to download it and provide the path)
		WebDriverManager.chromedriver().setup();

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to Google
        driver.get("https://www.google.com");

        // Find the search box element
        WebElement searchBox = driver.findElement(By.name("q"));

        // Click on the search box to focus it
        searchBox.click();
        Thread.sleep(5000);

        // Use the arrow keys to navigate to the 4th suggestion
        for (int i = 0; i < 4; i++) {
            searchBox.sendKeys(Keys.ARROW_DOWN);
        }

        // Press Enter to select the 4th suggestion
        searchBox.sendKeys(Keys.ENTER);

        // Wait for a while to see the result
        Thread.sleep(5000);

        // Close the browser
        driver.quit();
    }


	}


