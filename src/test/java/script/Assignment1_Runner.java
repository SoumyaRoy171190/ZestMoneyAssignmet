package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageobjects.AmazonHome;
import pageobjects.FlipkartHome;

public class Assignment1_Runner {

	WebDriver driver;
	int amazon_price = 0;
	int flipkart_price = 0;
	
	@Test(priority = 0)
	public void runAmazonSearch() {
		AmazonHome amazonHome = new AmazonHome(driver);
		amazonHome.openBrowser();
		amazonHome.openAmazon();
		amazonHome.performItemSearch();
		amazon_price = Integer.parseInt(amazonHome.extractPriceInformation());
		amazonHome.closeBrowser();
	}
	
	@Test(priority = 1)
	public void runFlipkartSearch() {
		FlipkartHome flipkartHome = new FlipkartHome(driver);
		flipkartHome.openBrowser();
		flipkartHome.openFlipkart();
		flipkartHome.performItemSearch();
		flipkart_price = Integer.parseInt(flipkartHome.extractPriceInformation());
		flipkartHome.closeBrowser();
	}
	
	@Test(priority = 2)
	public void performComparison() {
		if(amazon_price >= flipkart_price)
			System.out.println("Amazon Price : " +  amazon_price + " :: Flipkart Price : " + flipkart_price + "\n It's better to buy this product form Flipkart");
		else
			System.out.println("Amazon Price : " +  amazon_price + " :: Flipkart Price : " + flipkart_price + "\n It's better to buy this product form Amazon");
	}
}
