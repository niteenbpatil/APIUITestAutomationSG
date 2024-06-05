package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class Inventory_Main_Page extends DriverFactory {

	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
	Logger log = LogManager.getLogger(this.getClass().getName());

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement btnAddToCart;

	@FindBy(xpath = "//div[@id='shopping_cart_container']//span[contains(@class, 'shopping_cart_badge')]")
	private WebElement redBadgeCounter;

	@FindBy(xpath = "//div[@id='shopping_cart_container']/a[contains(@class, 'shopping_cart_link')]")
	private WebElement btnCart;

	public Inventory_Main_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickAddButton() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddToCart));
		btnAddToCart.click();
	}

	public void visibilityOfRedBadgeCounter() {
		try {assertTrue(redBadgeCounter.isDisplayed());
		} catch(Exception e) {
			log.info("Exception raised while checking visibility of RedBadgeCounter icon: " + e.getMessage());
		}
	}

	public void selectItems(int n) {
		for(int i=1; i <= n; i++) {
			String xpath = String.format("//div[contains(@class, 'inventory_list')]/div[%s]//button[1]", i);
			WebElement buttonAddToCard = driver.findElement(By.xpath(xpath));
			buttonAddToCard.click();
		}
	}

	public void clickOnCart() {
		wait.until(ExpectedConditions.visibilityOf(btnCart)) ;
		btnCart.click();
	}
	public void addItemInCartByName(String ItemName) {
		String xpath = String.format("//div[contains(., '%s')]/parent::a/parent::div/following-sibling::div/button", ItemName);
		WebElement itemElement = driver.findElement(By.xpath(xpath));
		itemElement.click();
	}
}

