package hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static hardcore.test.HardcorePricingCalculatorTest.SEARCH_TERM;

public class HardcoreGoogleHomePage extends AbstractHardcorePage {

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

    public HardcorePricingCalculatorFormPage getCalculatorPageFromSearch() {
        List<WebElement> searchResultsList = driver.findElements(By.xpath("//div[@class='gsc-webResult gsc-result']//a"))
                .stream().toList();
        for (WebElement element : searchResultsList) {
            if (element.getText().equals(SEARCH_TERM)) {
                return new HardcorePricingCalculatorFormPage(driver, element.getAttribute("href"));
            }
        }
        return null;
    }
}
