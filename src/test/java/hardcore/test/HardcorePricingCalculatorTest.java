package hardcore.test;

import hardcore.page.HardcoreCloudHomePage;
import hurt_me_plenty.exceptions.NoSuchResultException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HardcorePricingCalculatorTest {
    public WebDriver driver;
    private final String HOMEPAGE_URL = "https://cloud.google.com/";
    private final String SEARCH_TERM = "Google Cloud Pricing Calculator";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(2, ChronoUnit.SECONDS));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

    @Test(description = "Assert that manual and expecting sums are equal")
    public void TestResultingSumEqualsExpectingSum() throws NoSuchResultException, ParseException, InterruptedException {
        Assert.assertTrue(new HardcoreCloudHomePage(driver)
                .openPage(HOMEPAGE_URL)
                .searchForTerm(SEARCH_TERM)
                .openPage()
                .fillAllFieldsAccordingToTerms()
                .addToEstimate()
                        .getEstimateSum()
                        .requestEmail()
                        .openEmailPageInNewTab()
                        .createNewMailBox()
                        .waitForMail()
                .expectThatSumInEstimateAndEmailAreEqual()
        );
    }
}
