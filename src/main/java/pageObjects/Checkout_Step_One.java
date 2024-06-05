package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.time.Duration;

public class Checkout_Step_One extends DriverFactory {

	WebDriver driver;

	@FindBy(id = "first-name")
	private WebElement txtFirstName;

	@FindBy(id = "last-name")
	private WebElement txtLastName;

	@FindBy(id = "postal-code")
	private WebElement txtPostalCode;

	@FindBy(id = "continue")
	private WebElement btnContinue;

	public Checkout_Step_One(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));


	public void enterFirstName(String firstName) {
		wait.until(ExpectedConditions.visibilityOf(txtFirstName));
		txtFirstName.sendKeys(firstName);
	}
	public void enterLastName(String lastName) {
		wait.until(ExpectedConditions.visibilityOf(txtLastName));
		txtLastName.sendKeys(lastName);
	}
	public void enterZipCode(String zipCode) {
		wait.until(ExpectedConditions.visibilityOf(txtPostalCode));
		txtPostalCode.sendKeys(zipCode);
	}
	public void clickContinueButton() {
		wait.until(ExpectedConditions.visibilityOf(btnContinue));
		btnContinue.click();
	}
}
