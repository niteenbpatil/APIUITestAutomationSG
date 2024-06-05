package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/functionalTests/api",
        glue = {"stepDefs"},
        monochrome = true,
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber1.json",
                "junit:target/cucumber-reports/cucumber1.xml",
                "html:target/cucumber-reports/cucumber1.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class TestRunnerAPI {
}