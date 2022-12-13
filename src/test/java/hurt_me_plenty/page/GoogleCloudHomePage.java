package hurt_me_plenty.page;

import hurt_me_plenty.exceptions.NoSuchResultException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleCloudHomePage extends Page{

    private String searchTerm;

    private WebElement searchButton;

    private WebElement searchField;

    private WebElement searchResultLink;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openPage(String url) {
        driver.get(url);
        return this;
    }

    public GoogleCloudHomePage searchForTerm(String searchTerm) {
        this.searchTerm = searchTerm;
        return this;
    }

    public GooglePricingCalculatorPageForm followMatchedLink() throws NoSuchResultException {
        String resultingUrl = "";
        if (!searchResultLink.getText().equals(searchTerm)) {
            throw new NoSuchResultException("Search term is missing in search results");
        } else {
            return new GooglePricingCalculatorPageForm(driver, resultingUrl);
        }
    }
}
