package hurt_me_plenty.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class TestSettings {
    public static final String HOMEPAGE_URL = "https://cloud.google.com/";
    public static final String CALCULATOR_PAGE_URL = "https://cloud.google.com/products/calculator";
    public static final String SEARCH_TERM = "Google Cloud Pricing Calculator";
    public static final String TESTING_ENVIRONMENT = TestEnvironmentReader.getTestData("env.settings");
    protected WebDriver driver;

    @BeforeClass()
    public void webDriverManagerSetup() {
        switch (System.getProperty("browser")) {
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            case "safari" -> WebDriverManager.safaridriver().setup();
            default -> WebDriverManager.chromedriver().setup();
        }
    }

    @BeforeMethod()
    public void browserSetup() {
        switch (System.getProperty("browser")) {
            case "firefox" -> driver = new FirefoxDriver();
            case "safari" -> driver = new SafariDriver();
            default -> driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(2, ChronoUnit.SECONDS));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
    }

}