package stepDefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Login_Page;
import utils.DriverFactory;
import utils.ReadConfigFile;

import java.time.Duration;
import java.util.List;

public class UI_LoginStep {

    WebDriver driver = DriverFactory.getDriver();
    Login_Page loginPage = new Login_Page(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Logger log = LogManager.getLogger(this.getClass().getName());

    @Given("User is on login page")
    public void user_is_on_login_page() {
        log.info("Lunching browser and site https://www.saucedemo.com/ ");
    }

    @When("User enters invalid username or invalid password")
    public void user_enters_invalid_username_or_invalid_password(DataTable credentials) throws Exception {
        List<List<String>> data = credentials.asLists();
        for (int i = 1; i < data.size(); i++) {
            //In the List first element is the DataTable column name
            //And starting from next element there are respective column values (username/password)
            loginPage.sendLoginCredentials(data.get(i).get(0), data.get(i).get(1));
            clicks_on_login_button();
            should_be_visible_message_for_incorrect_credentials();
        }
    }

    @When("User enters invalid {string} username or invalid {string} password")
    public void user_enters_invalid_username_or_invalid_password1(String username, String password) {
        loginPage.sendLoginCredentials(username, password);
    }

    @And("User clicks on login button")
    public void user_clicks_on_login_button() throws Exception {
        loginPage.clickLoginButton();
    }

    @Then("Should be visible message for incorrect credentials")
    public void should_be_visible_message_for_incorrect_credentials() throws Exception {
        loginPage.visibilityOfInvalidCredentialsMsg();
        Thread.sleep(200);
    }

    @When("User enter a valid credentials")
    public void user_enter_a_valid_credentials() {
        String username = ReadConfigFile.readConfig("username");
        String password = ReadConfigFile.readConfig("password");
        loginPage.sendLoginCredentials(username, password);
    }

    @And("Clicks on login button")
    public void clicks_on_login_button() throws Exception {
        loginPage.clickLoginButton();
        Thread.sleep(1000);
    }

    @Then("User should login successfully and get access to inventory page")
    public void user_should_login_successfully_and_get_access_to_inventory_page() {
        loginPage.accessToInventoryPage();
    }

}


