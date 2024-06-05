package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver driver;
    public static String browser = ReadConfigFile.readConfig("browser");
    public static String webSiteUrl = ReadConfigFile.readConfig("URL");
    public static DesiredCapabilities cap;

    public static void createLocalDriver() {
        try {
            switch (browser.toLowerCase()) {

                case "chrome":
                    System.out.println("Chrome browser is getting instantiated..");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    //options - to set Capabilities for the chrome instance.
                    //ChromeOptions options = new ChromeOptions();
                    //driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    //DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                    //capabilities.setCapability("marionette", true);
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
            }
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
            driver.get(webSiteUrl);
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createRemoteDriver() throws MalformedURLException {
        try {
            switch (browser.toLowerCase()) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    cap = new DesiredCapabilities();
                    cap.setCapability(CapabilityType.BROWSER_NAME, "chrome");
                    break;
                case "firefox":
                    WebDriverManager.chromedriver().setup();
                    cap = new DesiredCapabilities();
                    cap.setCapability(CapabilityType.BROWSER_NAME, "firefox");
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    cap = new DesiredCapabilities();
                    cap.setCapability(CapabilityType.BROWSER_NAME, "ie");
                    break;
            }
            driver = new RemoteWebDriver(new URL("http://192.168.0.12:4444/wd/hub"), cap);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
            driver.get(webSiteUrl);
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void tearDownDrivers() {
        if (driver != null) {
            driver.quit();
        }
    }
}
