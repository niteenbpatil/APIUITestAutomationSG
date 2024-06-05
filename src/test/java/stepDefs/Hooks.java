package stepDefs;

import ApiUtils.RestAssuredExtension;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverFactory;

import java.net.MalformedURLException;

public class Hooks extends DriverFactory {

    Logger log = LogManager.getLogger(this.getClass().getName());
    private String environmentType = System.getProperty("environmentType");

    @Before("@front-end")
    public void setup() throws MalformedURLException {
        switch (environmentType.toLowerCase()) {
            case "local":
                log.error("Initiating the Local WebDriver instance...");
                createLocalDriver();
                break;
            case "remote":
                log.error("Initiating the Remote WebDriver instance...");
                createRemoteDriver();
                break;
            default:
                log.error("Invalid environmentType passed it should be with LOCAL or REMOTE");
        }
    }

    @After("@front-end")
    public void tearDown() {
        tearDownDrivers();
    }

    @Before("@back-end")
    public void TestSetup() {
        RestAssuredExtension RestAssuredExtension = new RestAssuredExtension();
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        //validate if scenario has failed
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");
        }
    }
}
