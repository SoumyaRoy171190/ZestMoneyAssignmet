package pageobjects;

import org.openqa.selenium.WebDriver;

public class AmazonHome {

	private WebDriver driver;

	public AmazonHome(WebDriver driver) {
		this.driver = driver;
	}

	private BrowserOperations br_ops = new BrowserOperations(driver);

	private String strBaseSearchInputBxXpath = "//input[@id='twotabsearchtextbox']";
	private String strBaseSearchIconXpath = "//span[@id='nav-search-submit-text']";
	private String strBrandAppleCheckBxXpath = "//span[text()='Apple']/preceding::input[@type='checkbox']";
	private String strSearchItemListXpath = "//div[contains(@class,'search-results') and contains(@class,'main-slot')]/div[@data-component-id]";
	private String strSearchItemNameXpath = "//div[contains(@class,'search-results') and contains(@class,'main-slot')]/div[@data-component-id][XXXX]//h2//span";
	private String strSearchItemPriceXpath = "//div[contains(@class,'search-results') and contains(@class,'main-slot')]/div[@data-component-id][XXXX]//span[@data-a-color='price']/span[1]";

	public void openBrowser() {
		br_ops.initiateBrowser("Chrome");
	}

	public void openAmazon() {
		br_ops.navigateToURL("https://www.amazon.in/");
	}

	public void performItemSearch() {
		br_ops.enterTextInElement(strBaseSearchInputBxXpath, "Amazon Search Input Field", "iPhone XR (64GB) - Yellow");
		br_ops.clickElement(strBaseSearchIconXpath, "Search Icon");
		br_ops.clickElement(strBrandAppleCheckBxXpath, "Apple Brand CheckBox");
	}

	public String extractPriceInformation() {

		String priceInfo = "";
		for (int index = 1; index <= br_ops.findTotalIndexCountFromListResult(strSearchItemListXpath); index++) {
			String itemName = br_ops
					.getElementText(br_ops.getFormatteLocator(strSearchItemNameXpath, Integer.toString(index)));
			if (itemName.contains("iPhone") && itemName.contains("XR") && itemName.contains("64")
					&& itemName.contains("GB")) {
				priceInfo = br_ops
						.getElementText(br_ops.getFormatteLocator(strSearchItemPriceXpath, Integer.toString(index)));
				break;
			}
		}
		return priceInfo.replace("₹", "").replace(",", "");
	}

	public void closeBrowser() {
		br_ops.tearDownBrowser();
	}
}
