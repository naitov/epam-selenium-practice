package hardcore.page;

import hardcore.exceptions.HardcoreNoSuchResultException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HardcoreGoogleHomePage extends AbstractHardcorePage {
    private static final String SEARCH_TERM = "Google Cloud Pricing Calculator";

    @FindBy(name = "q")
    private WebElement searchField;

    public HardcoreGoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public HardcoreGoogleHomePage openHomePage(String url) {
        driver.get(url);
        return this;
    }

    public HardcoreGoogleHomePage searchForTerm() {
        searchField.sendKeys(SEARCH_TERM);
        searchField.submit();
        return this;
    }

    public HardcorePricingCalculatorFormPage getCalculatorPageFromSearch() throws HardcoreNoSuchResultException {
        WebElement searchResultLink = createNewClickableElement(10, "//b[text()='Google Cloud Pricing Calculator']/parent::a");
        WebElement SearchResultText = createNewPresenceElement(10, "//b[text()='Google Cloud Pricing Calculator']/parent::a/child::b");

        if (SearchResultText.getText().equals(SEARCH_TERM)) {
            return new HardcorePricingCalculatorFormPage(driver, searchResultLink.getAttribute("href"));
        } else {
            throw new HardcoreNoSuchResultException("Search term is missing in search results");
        }
    }
}
