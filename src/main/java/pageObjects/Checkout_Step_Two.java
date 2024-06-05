package pageObjects;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.time.Duration;

public class Checkout_Step_Two extends DriverFactory {

	WebDriver driver;

	@FindBy(id = "finish")
	private WebElement btnFinish;

	@FindBy(className = "summary_tax_label")
	private WebElement lblTax;

	@FindBy(className = "summary_total_label")
	private WebElement lblTotalAmt;

	public Checkout_Step_Two(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));

	public void checkTax(String taxValue) {
		wait.until(ExpectedConditions.visibilityOf(lblTax));
		Assert.assertEquals(lblTax.getText(), taxValue);
	}

	public void checkTotal(String totalValue) {
		wait.until(ExpectedConditions.visibilityOf(lblTotalAmt));
		Assert.assertEquals(lblTotalAmt.getText(), totalValue);
	}

}
