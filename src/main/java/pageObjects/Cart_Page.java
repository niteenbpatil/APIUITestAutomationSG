package pageObjects;

import org.junit.Assert;
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
import java.util.List;


public class Cart_Page extends DriverFactory {

    WebDriver driver;
    Logger log = LogManager.getLogger(this.getClass().getName());

    @FindBy(id = "checkout")
    private WebElement btnCheckout;

    @FindBy(css = "#cart_contents_container > div > div.cart_list > div.cart_item")
    private List<WebElement> cartItems;

    public Cart_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));

    public int itemCount() {
        return cartItems.size();
    }

    public void checkNumberOfItems(int actual, int expected) {
        try {
            Assert.assertEquals(expected, actual);
        } catch (Exception e) {
            log.info("Exception raised while checking items in shopping cart: " + e.getMessage());
        }
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.visibilityOf(btnCheckout));
        btnCheckout.click();
    }

}
