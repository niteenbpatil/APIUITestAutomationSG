package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Login_Page extends DriverFactory {
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
	Logger log = LogManager.getLogger(this.getClass().getName());

	@FindBy(id = "user-name")
	private WebElement userName;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "login-button")
	private WebElement btnLogin;

	@FindBy(className = "error-button")
	private WebElement btnError;

	public Login_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void sendLoginCredentials(String username, String pass) {
		userName.clear();
		password.clear();
		userName.sendKeys(username);
		password.sendKeys(pass);
	}

	public void clickLoginButton() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnLogin));
		btnLogin.click();
	}

	public void visibilityOfInvalidCredentialsMsg() throws Exception{
		wait.until(ExpectedConditions.visibilityOf(btnError));
		assertTrue(btnError.isDisplayed());
	}
	public void accessToInventoryPage() {
		String CurrentUrl = driver.getCurrentUrl();
		try{
			wait.until(ExpectedConditions.urlToBe(CurrentUrl));
			Assert.assertEquals(CurrentUrl, "https://www.saucedemo.com/inventory.html");
		}catch(Exception e) {
			log.info("Exception occurred while accessing inventory page: " + e.getMessage());
		}
	}
}