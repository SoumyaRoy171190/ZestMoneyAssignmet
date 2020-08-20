package pageobjects;

import org.openqa.selenium.WebDriver;

public class FlipkartHome {

	private WebDriver driver;

	private String strSearchItemInputBoxXpath = "//input[contains(@title,'Search for products, brands and more')]";
	private String strInputSearchBtnXpath = "//input[contains(@title,'Search for products, brands and more')]//following::button";
	private String strMobileCategoryLinkXpath = "(//span[text()='CATEGORIES']//following::a[text()='Mobiles'])[1]";
	private String strListSearchXpath = "(//div[@class='_1HmYoV _35HD7C'])[2]/div[@class='bhgxx2 col-12-12' and not(@style)]";
	private String strSearchItemNameXpath = "(//div[@class='_1HmYoV _35HD7C'])[2]/div[@class='bhgxx2 col-12-12' and not(@style)][XXX]//a/div[2]/div[1]/div[1]";
	private String strSerchItemProductPriceXpath = "(//div[@class='_1HmYoV _35HD7C'])[2]/div[@class='bhgxx2 col-12-12' and not(@style)][XXXX]//a/div[2]/div[2]/div[1]/div/div[1]";

	public FlipkartHome(WebDriver driver) {
		this.driver = driver;
	}

	private BrowserOperations br_ops = new BrowserOperations(driver);

	public void openBrowser() {
		br_ops.initiateBrowser("Chrome");
	}

	public void openFlipkart() {
		br_ops.navigateToURL("https://www.flipkart.com/");
	}

	public void performItemSearch() {
		br_ops.enterTextInElement(strSearchItemInputBoxXpath, "Flipkart Search Input Field",
				"iPhone XR (64GB) - Yellow");
		br_ops.clickElement(strInputSearchBtnXpath, "Search Btn");
		br_ops.clickElement(strMobileCategoryLinkXpath, "Mobile Category Link");
	}

	public String extractPriceInformation() {

		String priceInfo = "";
		for (int index = 1; index <= br_ops.getElementListSize(strListSearchXpath); index++) {
			String itemName = br_ops
					.getElementText(br_ops.getFormatteLocator(strSearchItemNameXpath, Integer.toString(index)));
			if (itemName.contains("iPhone") && itemName.contains("XR") && itemName.contains("64")
					&& itemName.contains("GB")) {
				priceInfo = br_ops.getElementText(
						br_ops.getFormatteLocator(strSerchItemProductPriceXpath, Integer.toString(index)));
				break;
			}
		}
		return priceInfo.replace("₹", "").replace(",", "");
	}

	public void closeBrowser() {
		br_ops.tearDownBrowser();
	}

}
