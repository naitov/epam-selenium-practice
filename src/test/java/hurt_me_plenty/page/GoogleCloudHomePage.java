package hurt_me_plenty.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static hurt_me_plenty.test.TestSettings.SEARCH_TERM;

public class GoogleCloudHomePage extends AbstractPage {

    @FindBy(name = "q")
    private WebElement searchField;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openHomePage(String url) {
        driver.get(url);
        return this;
    }

    public GoogleCloudHomePage searchForTerm() {
        searchField.sendKeys(SEARCH_TERM);
        searchField.submit();
        return this;
    }

    public GooglePricingCalculatorFormPage getCalculatorPageFromSearch() {
        getElementWithClickableWait(WaitTimeouts.TEN_SEC, "//div[@class='gs-title']//a").click();
        return new GooglePricingCalculatorFormPage(driver);
    }
}
