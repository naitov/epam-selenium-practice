package hurt_me_plenty.test;

import hurt_me_plenty.form.GoogleCalculatorForm;
import hurt_me_plenty.form.GoogleCalculatorFormFactory;
import hurt_me_plenty.form.GoogleEstimateForm;
import hurt_me_plenty.form.GoogleEstimateFormFactory;
import hurt_me_plenty.page.GoogleCloudHomePage;
import hurt_me_plenty.page.GooglePricingCalculatorEstimatePage;
import hurt_me_plenty.page.GooglePricingCalculatorFormPage;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GooglePricingCalculatorTest extends TestSettings {
    @Test(description = "Actual and expected sums should be equal")
    public void actualAndExpectedSumsShouldBeEqual() throws ParseException {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat(String.format("Expecting url should be %s", CALCULATOR_PAGE_URL), driver.getCurrentUrl(), is(CALCULATOR_PAGE_URL));
        calculatorFormPage.setupFormPage();

        GoogleCalculatorForm calculatorForm = null;
        switch (TESTING_ENVIRONMENT) {
            case "dev" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithMinimumElements();
            case "staging" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElements();
            case "qa" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElementsExcludeGpu();
        }
        calculatorFormPage.fillAllNecessaryFields(TESTING_ENVIRONMENT, calculatorForm);
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.addToEstimate();
        double expectedSum = 2275.48; //manual check in the estimate
        double actualSum = estimatePage.getActualSumFromField();
        assertThat("Actual and expected sums should be equal", actualSum, equalTo(expectedSum));
    }

    @Test(description = "Resulting fields should contain expected parameters")
    public void resultingFieldsShouldContainExpectedValues() {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat(String.format("Expecting url should be %s", CALCULATOR_PAGE_URL), driver.getCurrentUrl(), is(equalTo(CALCULATOR_PAGE_URL)));
        calculatorFormPage.setupFormPage();

        GoogleCalculatorForm calculatorForm = null;
        switch (TESTING_ENVIRONMENT) {
            case "dev" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithMinimumElements();
            case "staging" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElements();
            case "qa" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElementsExcludeGpu();
        }
        calculatorFormPage.fillAllNecessaryFields(TESTING_ENVIRONMENT, calculatorForm);

        GoogleEstimateForm estimateForm;
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.addToEstimate();
        if ("dev".equals(TESTING_ENVIRONMENT)) {
            estimateForm = GoogleEstimateFormFactory.withDefaultFields();
            List<String> actualParameters = estimatePage.getActualTextFromField();
            assertThat("Expecting Region should match actual", actualParameters,
                    hasItems(estimateForm.getRegion()));
            assertThat("Expecting Provisioning Model should match actual", actualParameters,
                    hasItems(estimateForm.getProvisioningModel()));
            assertThat("Expecting Instance Type should match actual", actualParameters,
                    hasItems(estimateForm.getInstanceType()));
            assertThat("Expecting Operation System should match actual", actualParameters,
                    hasItems(estimateForm.getOperatingSystem()));
        } else {
            assertThat(calculatorForm, is(not(equalTo(null))));
            estimateForm = GoogleEstimateFormFactory.withUserFilledFields(calculatorForm);
            List<String> actualParameters = estimatePage.getActualTextFromField();
            assertThat("Expecting Region should match actual", actualParameters,
                    hasItems(estimateForm.getRegion()));
            assertThat("Expecting Commitment term should match actual", actualParameters,
                    hasItems(estimateForm.getCommitmentTerm()));
            assertThat("Expecting Provisioning Model should match actual", actualParameters,
                    hasItems(estimateForm.getProvisioningModel()));
            assertThat("Expecting Instance Type should match actual", actualParameters,
                    hasItems(estimateForm.getInstanceType()));
            assertThat("Expecting Operation System should match actual", actualParameters,
                    hasItems(estimateForm.getOperatingSystem()));
            assertThat("Expecting GPU should match actual", actualParameters,
                    hasItems(estimateForm.getGpuDies()));
            assertThat("Expecting SSD should match actual", actualParameters,
                    hasItems(estimateForm.getLocalSsd()));
        }
    }
}
