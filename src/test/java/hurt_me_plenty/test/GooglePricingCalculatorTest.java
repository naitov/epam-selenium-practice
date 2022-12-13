package hurt_me_plenty.test;

import hurt_me_plenty.exceptions.NoSuchResultException;
import hurt_me_plenty.page.GoogleCloudHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GooglePricingCalculatorTest {
    public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

    @Test(description = "Assert that new and resulting fields are equal")
    public void TestResultingPageTitleEqualsPasteName() throws NoSuchResultException {
        String googleCloudHomePageUrl = "https://cloud.google.com/";
        String searchTerm = "Google Cloud Platform Pricing Calculator";
        Assert.assertTrue(new GoogleCloudHomePage(driver)
                .openPage(googleCloudHomePageUrl)
                .searchForTerm(searchTerm)
                .followMatchedLink()
                .openPage()
                .fillFormAccordingToTerms()
                .addToEstimate()
                .expectThatEstimateBlockVMClassEqualsNewEstimateVMClass()
        );
    }

}
