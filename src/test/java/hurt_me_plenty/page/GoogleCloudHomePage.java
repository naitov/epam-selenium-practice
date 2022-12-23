package hurt_me_plenty.page;

import hurt_me_plenty.exceptions.NoSuchResultException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudHomePage extends AbstractPage {
    private static final String SEARCH_TERM = "Google Cloud Pricing Calculator";

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

    public GooglePricingCalculatorFormPage getCalculatorPageFromSearch() throws NoSuchResultException {
        WebElement searchResultLink = createNewClickableElement(10,
                "//b[text()='Google Cloud Pricing Calculator']/parent::a");
        WebElement SearchResultText = createNewPresenceElement(10,
                "//b[text()='Google Cloud Pricing Calculator']/parent::a/child::b");
        if (SearchResultText.getText().equals(SEARCH_TERM)) {
            return new GooglePricingCalculatorFormPage(driver, searchResultLink.getAttribute("href"));
        } else {
            throw new NoSuchResultException("Search term is missing in search results");
        }
    }
}
