package hurt_me_plenty.test;

import hurt_me_plenty.exceptions.NoSuchResultException;
import hurt_me_plenty.page.GoogleCloudHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class GooglePricingCalculatorTest {
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
    public void TestResultingSumEqualsExpectingSum() throws NoSuchResultException, ParseException {
        Double manualSum = 2275.48;
        Assert.assertTrue(new GoogleCloudHomePage(driver)
                .openPage(HOMEPAGE_URL)
                .searchForTerm(SEARCH_TERM)
                .openPage()
                .fillAllFieldsAccordingToTerms()
                .addToEstimate()
                .expectThatSumInEstimateBlockEqualsTo(manualSum)
        );
    }

    @Test(description = "Assert that new and resulting fields are equal")
    public void TestResultingFieldsEqualExpectingFields() throws NoSuchResultException {
        Map<String, Integer> fields = new HashMap<>() {{
            put("Provisioning model: Regular", 1);
            put("Region: Frankfurt", 1);
            put("Commitment term: 1 Year", 1);
        }};

        Assert.assertTrue(new GoogleCloudHomePage(driver)
                .openPage(HOMEPAGE_URL)
                .searchForTerm(SEARCH_TERM)
                .openPage()
                .fillAllFieldsAccordingToTerms()
                .addToEstimate()
                .expectThatResultingFieldsEqualTo(fields)
        );
    }

    @Test(enabled = false)
    public void TestResultingFieldsEqualExpectingFieldsPlusTwoLines() throws NoSuchResultException {
        Map<String, Integer> fields = new HashMap<>() {{
            put("Provisioning model: Regular", 1);
            put("Instance type: n1-standard-8", 1);
            put("Local SSD: 2x375 GiB", 1);
            put("Region: Frankfurt", 1);
            put("Commitment term: 1 Year", 1);
        }};

        Assert.assertTrue(new GoogleCloudHomePage(driver)
                .openPage(HOMEPAGE_URL)
                .searchForTerm(SEARCH_TERM)
                .openPage()
                .fillAllFieldsAccordingToTerms()
                .addToEstimate()
                .expectThatResultingFieldsEqualTo(fields)
        );
    }
}
