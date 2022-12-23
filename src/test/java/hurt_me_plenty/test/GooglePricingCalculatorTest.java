package hurt_me_plenty.test;

import hurt_me_plenty.exceptions.NoSuchResultException;
import hurt_me_plenty.page.GoogleCloudHomePage;
import hurt_me_plenty.page.GooglePricingCalculatorEstimatePage;
import hurt_me_plenty.page.GooglePricingCalculatorFormPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class GooglePricingCalculatorTest {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";

    private WebDriver driver;

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

    @Test(description = "Actual and expected sums should be equal")
    public void ActualAndExpectedSumsShouldBeEqual() throws NoSuchResultException, ParseException {
        Double expectedSum = 2275.48;
        GooglePricingCalculatorFormPage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm()
                .getCalculatorPageFromSearch();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.openFormPage();
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.fillAllNecessaryFields().addToEstimate();
        double actualSum = estimatePage.getActualSumFromField();
        assertThat("Actual and expected sums should be equal", actualSum, equalTo(expectedSum));
    }

    @Test(description = "Resulting fields should contain expected parameters")
    public void ResultingFieldsShouldContainExpectedParameters() throws NoSuchResultException {
        List<String> expectedParameters = Arrays.asList(
                "Provisioning model: Regular",
                "Instance type: n1-standard-8",
                "Local SSD: 2x375 GiB",
                "Region: Frankfurt",
                "Commitment term: 1 Year");
        GooglePricingCalculatorFormPage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm()
                .getCalculatorPageFromSearch();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.openFormPage();
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.fillAllNecessaryFields().addToEstimate();
        List<String> actualParameters = estimatePage.getActualTextFromField();
        assertThat("Resulting fields should contain expected parameters", actualParameters,
                hasItems(expectedParameters.toArray(new String[0])));
    }
}
