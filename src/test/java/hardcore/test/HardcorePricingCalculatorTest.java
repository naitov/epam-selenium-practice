package hardcore.test;

import hardcore.page.HardcoreGoogleHomePage;
import hardcore.page.HardcorePricingCalculatorEstimatePage;
import hardcore.page.HardcorePricingCalculatorFormPage;
import hardcore.page.YopmailHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class HardcorePricingCalculatorTest {
    public static final String HOMEPAGE_URL = "https://cloud.google.com/";
    public static final String SEARCH_TERM = "Google Cloud Pricing Calculator";
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(2, ChronoUnit.SECONDS));
    }

    @Test(description = "Actual and expected sums should be equal")
    public void ActualAndExpectedSumShouldBeEqual() throws ParseException, InterruptedException {
        HardcorePricingCalculatorFormPage homePage = new HardcoreGoogleHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm()
                .getCalculatorPageFromSearch();
        HardcorePricingCalculatorFormPage calculatorFormPage = homePage.openFormPage();
        HardcorePricingCalculatorEstimatePage estimatePage = calculatorFormPage.fillAllNecessaryFields()
                .addToEstimate();
        YopmailHomePage yopmailHomePage = estimatePage.createYopmailPage();
        yopmailHomePage.openEmailPageInNewTab()
                .createNewMailBoxWithRandomName()
                .switchToEstimatePage();
        double expectedSum = estimatePage.getEstimateSum();
        estimatePage.sendEmailFromPage()
                .switchToYopmail();
        double actualSum = yopmailHomePage.waitForMail().getActualSum();
        assertThat("Actual and expected sums should be equal", actualSum, equalTo(expectedSum));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
